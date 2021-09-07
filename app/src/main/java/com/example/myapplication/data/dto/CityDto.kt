package com.example.myapplication.data.dto

import com.google.gson.annotations.SerializedName

data class CityDto(
    @SerializedName("name")
    val name: String
)