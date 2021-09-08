package com.example.myapplication.data.domain

/*data class DailyWeatherItemDomain(
    val data: String,
    val minTemp: Int,
    val maxTemp: Int,
    val iconUrl: String
)*/

data class DailyWeatherItemDomain(
    val date: String,
    val tempMin: Int,
    val tempMax: Int,
    val tempMorn: Int,
    val tempDay: Int,
    val tempEve: Int,
    val tempNight: Int,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Int,
    val windSpeed: Int,
    val clouds: Int,
    val uvi: Double,
    val pop: Double,
    val rain: Double?,
    val snow: Double?,
    val main: String,
    val description: String,
    val iconUrl: String
)