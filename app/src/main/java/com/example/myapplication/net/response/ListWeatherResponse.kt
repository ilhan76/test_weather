package com.example.myapplication.net.response

import com.example.myapplication.data.dto.WeatherItemDto

data class ListWeatherResponse(
    val cod: Int,
    val message: String,
    val list: List<WeatherItemDto>
)