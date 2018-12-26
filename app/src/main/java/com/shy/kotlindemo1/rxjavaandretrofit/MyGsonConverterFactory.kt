package com.shy.kotlindemo1.rxjavaandretrofit

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.Charset

import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * @author sauceWu
 * @since 2017/7/4 15:41
 */

class MyGsonConverterFactory private constructor(private val gson: Gson) : Converter.Factory() {

//    init {
//        if (gson == null) {
//            throw NullPointerException("gson == null")
//        }
//    }


    override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?,
                                       retrofit: Retrofit?): Converter<ResponseBody, *>? {
        if (type === String::class.java
                || type === Byte::class.javaPrimitiveType
                || type === Byte::class.java
                || type === Char::class.javaPrimitiveType
                || type === Char::class.java
                || type === Double::class.javaPrimitiveType
                || type === Double::class.java
                || type === Float::class.javaPrimitiveType
                || type === Float::class.java
                || type === Int::class.javaPrimitiveType
                || type === Int::class.java
                || type === Long::class.javaPrimitiveType
                || type === Long::class.java
                || type === Short::class.javaPrimitiveType
                || type === Short::class.java) {
            return StringResponseBodyConverter.INSTANCE
        } else if (type === Boolean::class.javaPrimitiveType || type === Boolean::class.java) {
            return BooleanResponseBodyConverter.INSTANCE
        }

        val adapter = gson.getAdapter(TypeToken.get(type!!))
        return GsonResponseBodyConverter(gson, adapter)
    }

    override fun requestBodyConverter(type: Type?,
                                      parameterAnnotations: Array<Annotation>?, methodAnnotations: Array<Annotation>?, retrofit: Retrofit?): Converter<*, RequestBody>? {
        val adapter = gson.getAdapter(TypeToken.get(type!!))
        return GsonRequestBodyConverter(gson, adapter)
    }


    internal inner class GsonRequestBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<T, RequestBody> {
        private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
        private val UTF_8 = Charset.forName("UTF-8")

        @Throws(IOException::class)
        override fun convert(value: T): RequestBody {
            val buffer = Buffer()
            val writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
            val jsonWriter = gson.newJsonWriter(writer)
            adapter.write(jsonWriter, value)
            jsonWriter.close()
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString())
        }
    }


    internal inner class GsonResponseBodyConverter<T>(private val gson: Gson, private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {

        @Throws(IOException::class)
        override fun convert(value: ResponseBody): T {
            val jsonReader = gson.newJsonReader(value.charStream())
            try {
                return adapter.read(jsonReader)
            } finally {
                value.close()
            }
        }
    }

    internal class StringResponseBodyConverter : Converter<ResponseBody, String> {
        @Throws(IOException::class)
        override fun convert(value: ResponseBody): String {
            return value.string()
        }

        companion object {
            val INSTANCE = StringResponseBodyConverter()
        }
    }

    internal class BooleanResponseBodyConverter : Converter<ResponseBody, Boolean> {
        @Throws(IOException::class)
        override fun convert(value: ResponseBody): Boolean {
            return java.lang.Boolean.valueOf(value.string())
        }

        companion object {
            val INSTANCE = BooleanResponseBodyConverter()
        }
    }

    companion object {


        @JvmOverloads
        fun create(gson: Gson = Gson()): MyGsonConverterFactory {
            return MyGsonConverterFactory(gson)
        }
    }
}
