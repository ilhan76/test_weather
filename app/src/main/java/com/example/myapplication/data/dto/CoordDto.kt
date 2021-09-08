package com.example.myapplication.data.dto

import com.google.gson.annotations.SerializedName

data class CoordDto(
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("lat")
    val lat: Double
)