package com.shy.kotlindemo1.rxjavaandretrofit


/*
* 数据model
* */
data class WeatherInfoModel(val weatherinfo : weatherinfoBean) {

    data class weatherinfoBean(
            val city :String ,
            val cityId : String)
}