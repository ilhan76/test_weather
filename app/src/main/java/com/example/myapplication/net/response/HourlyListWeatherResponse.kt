package com.example.myapplication.net.response

import com.example.myapplication.data.dto.WeatherDto
import com.google.gson.annotations.SerializedName

data class HourlyListWeatherResponse(
    @SerializedName("hourly")
    val hourly: List<HourlyWeatherItemDto>?,
    val message: String?
)

data class HourlyWeatherItemDto(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("weather")
    val weather: List<WeatherDto>
)