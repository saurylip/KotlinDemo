package com.shy.kotlindemo1.rxjavaandretrofit

import android.app.Application
import com.readystatesoftware.chuck.ChuckInterceptor
import com.shy.kotlindemo1.ShyApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/*网络工场*/
class NetFactory {

    companion object {

        val SERVER_API = RetrofitClient().getInstance()

        class RetrofitClient{

            fun getInstance() : ServerApi{
                return Retrofit.Builder().
                        baseUrl(ApiStores.BASE_URL).
                        addCallAdapterFactory(ObserveOnMainCallAdapterFactory(AndroidSchedulers.mainThread())).
                        addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())).
                        client(OkhttpClientFactory.okhttpClient()).
                        addConverterFactory(MyGsonConverterFactory.create()).
                        build().
                        create(ServerApi::class.java)
            }
        }
    }

    /*创建okhttp客户端*/
    class OkhttpClientFactory{

        companion object {
            fun  okhttpClient() : OkHttpClient{
                return OkHttpClient.Builder()
                        .readTimeout(5, TimeUnit.SECONDS)
                        .writeTimeout(5, TimeUnit.SECONDS)
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .addNetworkInterceptor(FormatLogginInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addNetworkInterceptor(ChuckInterceptor(ShyApp.context))
                        .build()
            }
        }
    }
}