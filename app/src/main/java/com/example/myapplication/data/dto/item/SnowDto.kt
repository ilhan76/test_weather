package com.example.myapplication.data.dto.item

import com.google.gson.annotations.SerializedName

data class SnowDto(
    @SerializedName("3h")
    val snowVolume: Double // Snow volume for last 3 hours
)