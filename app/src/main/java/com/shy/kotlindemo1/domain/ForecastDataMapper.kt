package com.shy.kotlindemo1.domain

import com.shy.kotlindemo1.data.ForecastResult
import java.text.DateFormat
import java.util.*

import com.shy.kotlindemo1.domain.ForecastDataMapper.Forecast as ModelForecast

class ForecastDataMapper {

    fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    fun convertForecastListToDomain(list: List<ForecastResult.ForeCast>) : List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: ForecastResult.ForeCast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt),
                forecast.weather[0].description,
                forecast.temp.max.toInt(),
                forecast.temp.min.toInt(),
                generateIconUrl(forecast.weather[0].icon))}

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }

    private fun generateIconUrl(iconCode : String) : String{
        return "http://openweathermap.org/img/w/$iconCode.png"
    }

    data class ForecastList(val city: String, val country: String,val dailyForecast:List<Forecast>)
    data class Forecast(val date: String, val description: String, val high: Int,val low: Int,val iconUrl : String)

}

