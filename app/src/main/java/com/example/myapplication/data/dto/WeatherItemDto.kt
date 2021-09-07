package com.example.myapplication.data.dto

import com.example.myapplication.data.dto.item.*
import com.google.gson.annotations.SerializedName

data class WeatherItemDto(
    @SerializedName("main")
    val main: MainDto,
    @SerializedName("weather")
    val weather: WeatherDto,
    @SerializedName("clouds")
    val clouds: CloudsDto,
    @SerializedName("wind")
    val wind: WindDto,
    @SerializedName("pop")
    val pop: Double, // Probability of precipitation
    @SerializedName("rain")
    val rain: RainDto,
    @SerializedName("snow")
    val snow: SnowDto,
    @SerializedName("sys")
    val sys: SysDto,
    @SerializedName("city")
    val city: CityDto
)