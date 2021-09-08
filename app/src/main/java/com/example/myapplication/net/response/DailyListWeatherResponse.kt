package com.example.myapplication.net.response

import com.example.myapplication.data.dto.WeatherDto
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
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("uvi")
    val uvi: Double,
    @SerializedName("pop")
    val pop: Double,
    @SerializedName("rain")
    val rain: Double?,
    @SerializedName("snow")
    val snow: Double?,
    @SerializedName("weather")
    val weather: List<WeatherDto>
)

data class TempDto(
    @SerializedName("min")
    val min: Double,
    @SerializedName("max")
    val max: Double,
    @SerializedName("morn")
    val morn: Double,
    @SerializedName("day")
    val day: Double,
    @SerializedName("eve")
    val eve: Double,
    @SerializedName("night")
    val night: Double
)