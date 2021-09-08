package com.example.myapplication.net.response

import com.example.myapplication.data.dto.CoordDto
import com.example.myapplication.data.dto.WeatherDto
import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("coord")
    val coord: CoordDto? = null,
    @SerializedName("weather")
    val weatherDto: List<WeatherDto>? = null,
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("name")
    val name: String? = null,
    val message: String?
)