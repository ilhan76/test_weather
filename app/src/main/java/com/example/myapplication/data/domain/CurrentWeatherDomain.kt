package com.example.myapplication.data.domain

data class CurrentWeatherDomain(
    val temp: Int,
    val feelsLike: Int,
    val windSpeed: Int,
    val humidity: Int,
    val uvi: Double,
    val pressure: Int,
    val visibility: Int,
    val main: String,
    val description: String,
    val iconUrl: String
)