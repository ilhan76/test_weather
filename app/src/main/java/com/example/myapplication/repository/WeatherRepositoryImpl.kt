package com.example.myapplication.repository

import android.util.Log
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
    private val TAG: String = this::class.java.simpleName

    override fun getFiveDayWeather(): Flow<RepositoryResponse<List<WeatherItemDomain>>>  =
        flow {
            emit(withContext(Dispatchers.IO){
                try {
                    Log.d(TAG, "getFiveDayWeather: Success")
                    val listWeatherResponse = remoteDatasource.getWeatherListAsync().await()
                    val listWeatherDomain = listWeatherResponse.list?.map { it.toDomain() }
                    RepositoryResponse(listWeatherDomain, null)
                } catch (e: Exception){
                    Log.d(TAG, "getFiveDayWeather: ${e.localizedMessage}")
                    RepositoryResponse<List<WeatherItemDomain>>(null, e.localizedMessage)
                }
            })
        }
}