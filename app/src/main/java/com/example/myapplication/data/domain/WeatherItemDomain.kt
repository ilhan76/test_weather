package com.example.myapplication.data.domain

data class WeatherItemDomain(
    val tempMax: Double,
    val tempMin: Double,
    val weatherDescription: String,
    val date: String
)