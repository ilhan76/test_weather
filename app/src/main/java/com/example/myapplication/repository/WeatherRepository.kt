package com.example.myapplication.repository

import com.example.myapplication.data.domain.WeatherItemDomain
import com.example.myapplication.net.response.RepositoryResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getFiveDayWeatherAsync() : Flow<RepositoryResponse<List<WeatherItemDomain>>>
}