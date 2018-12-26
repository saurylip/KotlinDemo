package com.shy.kotlindemo1.data

/*
* {
city: {
id: 2643743,
name: "London",
coord: {
lon: -0.1277,
lat: 51.5073
},
country: "GB",
population: 1000000
},
cod: "200",
message: 8.4561548,
cnt: 7,
list: [
{
dt: 1545217200,
temp: {
day: 8.5,
min: 6.43,
max: 8.5,
night: 7.37,
eve: 6.43,
morn: 8.5
},
pressure: 1011.88,
humidity: 97,
weather: [
{
id: 500,
main: "Rain",
description: "light rain",
icon: "10d"
}
],
speed: 4.65,
deg: 229,
clouds: 12,
rain: 0.49
}
]
}
*
* */



data class ForecastResult(val city: City,
                     val cnt: Int,
                     val cod: String,
                     val list: List<ForeCast>,
                     val message: Double) {


    data class ForeCast(
            val clouds: Int,
            val deg: Int,
            val dt: Long,
            val humidity: Int,
            val pressure: Double,
            val rain: Double,
            val speed: Double,
            val temp: Temp,
            val weather: List<Weather>
    )

    data class Temp(
            val day: Double,
            val eve: Double,
            val max: Double,
            val min: Double,
            val morn: Double,
            val night: Double
    )

    data class Weather(
            val description: String,
            val icon: String,
            val id: Int,
            val main: String
    )

    data class City(
            val coord: Coord,
            val country: String,
            val id: Int,
            val name: String,
            val population: Int
    )

    data class Coord(
            val lat: Double,
            val lon: Double
    )

}