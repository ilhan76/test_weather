package com.example.myapplication.data.dto.item

import com.google.gson.annotations.SerializedName

data class SysDto(
    @SerializedName("pod")
    val pod: String // Part of the day (n - night, d - day)
)