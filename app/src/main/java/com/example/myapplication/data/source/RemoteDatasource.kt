package com.example.myapplication.data.source

import com.example.myapplication.net.response.HourlyListWeatherResponse
import com.example.myapplication.net.response.ListWeatherResponse

interface RemoteDatasource {
    suspend fun getWeatherList(): ListWeatherResponse
    suspend fun getHourlyListWeather(): HourlyListWeatherResponse
}