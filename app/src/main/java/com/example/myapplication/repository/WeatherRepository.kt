package com.example.myapplication.repository

import com.example.myapplication.data.domain.CurrentWeatherDomain
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.data.domain.WeatherItemDomain
import com.example.myapplication.net.response.RepoResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(): Flow<RepoResponse<CurrentWeatherDomain>>
    fun getHourlyListWeather(): Flow<RepoResponse<List<HourlyWeatherItemDomain>>>
}