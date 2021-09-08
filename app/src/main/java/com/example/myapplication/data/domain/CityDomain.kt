package com.example.myapplication.data.domain

data class CityDomain(
    val lon: Double,
    val lat: Double,
    val main: String,
    val description: String,
    val icon: String,
    val id: Long,
    val name: String
)