package com.example.myapplication.net.response

import com.example.myapplication.data.dto.CityDto
import com.example.myapplication.data.dto.WeatherItemDto
import com.google.gson.annotations.SerializedName

data class ListWeatherResponse(
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("message")
    val message: String?,
    @SerializedName("list")
    val list: List<WeatherItemDto>?,
    @SerializedName("city")
    val city: CityDto?
)