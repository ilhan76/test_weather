package com.example.myapplication.net.response

import com.example.myapplication.data.dto.item.WeatherDto
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val current: CurrentDto?,
    val message: String?
)

data class CurrentDto(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<WeatherDto>
)