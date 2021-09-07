package com.example.myapplication.data.dto.item

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("main")
    val main: String, // Group of weather parameters (Rain, Snow, Extreme etc.)
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String // Weather icon id
)