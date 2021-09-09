package com.example.myapplication.data.source

import com.example.myapplication.net.response.*

interface RemoteDatasource {
    suspend fun getCityName(latitude: Double, longitude: Double): CityNameResponse
    suspend fun getCityCoordinate(cityName: String): CityResponse
    suspend fun getCurrentWeather(latitude: Double, longitude: Double) : CurrentWeatherResponse
    suspend fun getHourlyListWeather(latitude: Double, longitude: Double): HourlyListWeatherResponse
    suspend fun getDailyListWeather(latitude: Double, longitude: Double): DailyListWeatherResponse
}