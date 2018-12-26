package com.shy.kotlindemo1.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

class Request(val zipCode : String) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +"forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }


    public fun execute() : ForecastResult{
        val jsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(jsonStr,ForecastResult::class.java)
    }


    @Deprecated("使用execute替换")
    public fun run(){
        val jsonStr = URL("url").readText()
        Log.d("result",jsonStr)
    }
}