package com.example.myapplication.net.response

import com.google.gson.annotations.SerializedName

data class CityNameResponse(
    @SerializedName("name")
    val name: String?,
    val message: String?
)