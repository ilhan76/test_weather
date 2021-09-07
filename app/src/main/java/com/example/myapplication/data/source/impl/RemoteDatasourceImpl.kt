package com.example.myapplication.data.source.impl

import android.util.Log
import com.example.myapplication.data.source.RemoteDatasource
import com.example.myapplication.net.ApiService
import com.example.myapplication.net.response.HourlyListWeatherResponse
import com.example.myapplication.net.response.ListWeatherResponse
import kotlinx.coroutines.*

class RemoteDatasourceImpl : RemoteDatasource {
    private val TAG: String = this::class.java.simpleName

    override suspend fun getWeatherList(): ListWeatherResponse {
        return try {
            Log.d(TAG, "getWeatherListAsync: Remote")
            ApiService.create().getListWeather(
                latitude = 51.5073,
                longitude = -0.1277,
                appid = "0b0f9f5b54968d5ad5c5788c304286f3",
                language = "ru"
            )
        } catch (e: Exception) {
            Log.d(TAG, "getWeatherListAsync: ${e.localizedMessage}")
            ListWeatherResponse(-1, e.localizedMessage, null, null)
        }
    }

    override suspend fun getHourlyListWeather(): HourlyListWeatherResponse {
        return try {
            Log.d(TAG, "getHourlyListWeatherAsync: Remote")
            ApiService.create().getHourlyListWeather(
                latitude = 51.5073,
                longitude = -0.1277,
                appid = "0b0f9f5b54968d5ad5c5788c304286f3",
                language = "ru",
                units = "metric"
            )
        } catch (e: Exception){
            Log.d(TAG, "getHourlyListWeatherAsync: Error ${e.localizedMessage}")
            HourlyListWeatherResponse(null,  e.localizedMessage)
        }
    }
}