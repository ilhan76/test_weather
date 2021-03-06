package com.example.myapplication.repository

import com.example.myapplication.data.domain.CityDomain
import com.example.myapplication.data.domain.CurrentWeatherDomain
import com.example.myapplication.data.domain.DailyWeatherItemDomain
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.net.response.RepoResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCityName(latitude: Double, longitude: Double): Flow<RepoResponse<String>>
    fun getCityCoordinate(cityName: String): Flow<RepoResponse<CityDomain>>
    fun getCurrentWeather(latitude: Double, longitude: Double): Flow<RepoResponse<CurrentWeatherDomain>>
    fun getHourlyListWeather(latitude: Double, longitude: Double): Flow<RepoResponse<List<HourlyWeatherItemDomain>>>
    fun getDailyListWeather(latitude: Double, longitude: Double): Flow<RepoResponse<List<DailyWeatherItemDomain>>>
}