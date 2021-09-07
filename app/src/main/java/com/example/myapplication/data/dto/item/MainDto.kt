package com.example.myapplication.data.dto.item

import com.google.gson.annotations.SerializedName

data class MainDto(
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("pressure")
    val pressure: Double, // Atmospheric pressure on the sea level by default, hPa
    @SerializedName("humidity")
    val humidity: Int // Humidity, %
)