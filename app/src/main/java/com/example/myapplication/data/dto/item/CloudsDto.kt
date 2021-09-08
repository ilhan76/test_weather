package com.example.myapplication.data.dto.item

import com.google.gson.annotations.SerializedName

data class CloudsDto(
    @SerializedName("all")
    val all: Int // Cloudiness, %
)