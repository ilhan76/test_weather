package com.example.myapplication.data.domain

data class DailyWeatherItemDomain(
    val data: String,
    val minTemp: Int,
    val maxTemp: Int,
    val iconUrl: String
)