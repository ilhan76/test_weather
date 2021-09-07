package com.example.myapplication.data.dto.item

import com.google.gson.annotations.SerializedName

data class RainDto(
    @SerializedName("3h")
    val rainVolume: Double // Rain volume for last 3 hours, mm
)