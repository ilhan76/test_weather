package com.example.myapplication.data.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)