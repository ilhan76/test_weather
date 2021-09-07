package com.example.myapplication.repository

import com.example.myapplication.data.domain.WeatherItemDomain
import com.example.myapplication.data.source.RemoteDatasource
import com.example.myapplication.net.response.RepositoryResponse
import com.example.myapplication.util.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception

class WeatherRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
): WeatherRepository {
    override fun getFiveDayWeatherAsync(): Flow<RepositoryResponse<List<WeatherItemDomain>>>  =
        flow {
            emit(withContext(Dispatchers.IO){
                try {
                    val listWeatherResponse = remoteDatasource.getWeatherListAsync().await()
                    val listWeatherDomain = listWeatherResponse.list?.map { it.toDomain() }
                    RepositoryResponse(listWeatherDomain, null)
                } catch (e: Exception){
                    RepositoryResponse<List<WeatherItemDomain>>(null, e.localizedMessage)
                }
            })
        }
}