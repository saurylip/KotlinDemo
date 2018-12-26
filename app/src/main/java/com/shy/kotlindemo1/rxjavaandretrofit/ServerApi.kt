package com.shy.kotlindemo1.rxjavaandretrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerApi {

    @GET(ApiStores.GET_WEATHER)
    fun getWeather(@Path("cityId") cityid : String ) : Observable<WeatherInfoModel>

}