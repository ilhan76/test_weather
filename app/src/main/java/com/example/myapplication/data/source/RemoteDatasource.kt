package com.example.myapplication.data.source

import com.example.myapplication.net.response.CurrentWeatherResponse
import com.example.myapplication.net.response.HourlyListWeatherResponse

interface RemoteDatasource {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double) : CurrentWeatherResponse
    suspend fun getHourlyListWeather(latitude: Double, longitude: Double): HourlyListWeatherResponse
}