package com.example.myapplication.net.response

import com.example.myapplication.data.dto.item.WeatherDto
import com.google.gson.annotations.SerializedName

data class DailyListWeatherResponse(
    @SerializedName("daily")
    val daily: List<DailyWeatherItemDto>?,
    val message: String?
)

data class DailyWeatherItemDto(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("temp")
    val temp: TempDto,
    @SerializedName("weather")
    val weather: List<WeatherDto>
)

data class TempDto(
    @SerializedName("min")
    val min: Double,
    @SerializedName("max")
    val max: Double
)