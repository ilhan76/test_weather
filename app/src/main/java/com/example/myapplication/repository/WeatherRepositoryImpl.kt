package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.data.domain.WeatherItemDomain
import com.example.myapplication.data.source.RemoteDatasource
import com.example.myapplication.net.response.RepoResponse
import com.example.myapplication.util.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception

class WeatherRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : WeatherRepository {
    private val TAG: String = this::class.java.simpleName

    override fun getFiveDayWeather(): Flow<RepoResponse<List<WeatherItemDomain>>> =
        flow {
            emit(withContext(Dispatchers.IO) {
                try {
                    Log.d(TAG, "getFiveDayWeather: Repository")
                    val listWeatherResponse = remoteDatasource.getWeatherList()
                    val listWeatherDomain = listWeatherResponse.list?.map { it.toDomain() }
                    RepoResponse(listWeatherDomain, null)
                } catch (e: Exception) {
                    Log.d(TAG, "getFiveDayWeather: ${e.localizedMessage}")
                    RepoResponse<List<WeatherItemDomain>>(null, e.localizedMessage)
                }
            })
        }

    override fun getHourlyListWeather(): Flow<RepoResponse<List<HourlyWeatherItemDomain>>> =
        flow {
            emit(withContext(Dispatchers.IO){
                try {
                    Log.d(TAG, "getHourlyListWeather: Repository")
                    val hourlyListWeatherResponse = remoteDatasource.getHourlyListWeather()
                    val list = hourlyListWeatherResponse.hourly?.map { it.toDomain() }
                    RepoResponse(list, null)
                } catch (e: Exception){
                    Log.d(TAG, "getHourlyListWeather: Error ${e.localizedMessage}")
                    RepoResponse<List<HourlyWeatherItemDomain>>(null, e.localizedMessage)
                }
            })
        }
}