package com.example.myapplication.data.dto.item

import com.google.gson.annotations.SerializedName

data class WindDto(
    @SerializedName("speed")
    val speed: Double,
    @SerializedName("deg")
    val deg: Int // Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
)