package com.example.myapplication.data.source

import com.example.myapplication.net.response.CurrentWeatherResponse
import com.example.myapplication.net.response.HourlyListWeatherResponse
import com.example.myapplication.net.response.ListWeatherResponse

interface RemoteDatasource {
    suspend fun getCurrentWeather() : CurrentWeatherResponse
    suspend fun getHourlyListWeather(): HourlyListWeatherResponse
}