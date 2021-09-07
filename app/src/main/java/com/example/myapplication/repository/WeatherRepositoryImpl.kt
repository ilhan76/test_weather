package com.example.myapplication.repository

import com.example.myapplication.data.domain.WeatherItemDomain
import com.example.myapplication.data.source.RemoteDatasource
import com.example.myapplication.net.response.RepositoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    val remoteDatasource: RemoteDatasource
): WeatherRepository {
    override fun getFiveDayWeatherAsync(): Flow<RepositoryResponse<List<WeatherItemDomain>>>  =
        flow {

        }
}