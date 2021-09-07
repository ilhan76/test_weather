package com.example.myapplication.data.domain

data class WeatherItemDomain(
    val tempMax: Double,
    val tempMin: Double,
    val pressure: Double,
    val humidity: Int,
    val mainWeather: String,
    val weatherDescription: String,
    val iconUrl: String,
    val cloudiness: Int,
    val speed: Double,
    val deg: Int,
    val probabilityOfPrecipitation: Double,
    val rainVolume: Double?,
    val snowVolume: Double?,
    val partOfDay: String
)