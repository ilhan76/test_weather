package com.example.myapplication.data.source.impl

import android.util.Log
import com.example.myapplication.data.source.RemoteDatasource
import com.example.myapplication.net.ApiService
import com.example.myapplication.net.response.CurrentWeatherResponse
import com.example.myapplication.net.response.HourlyListWeatherResponse
import com.example.myapplication.net.response.ListWeatherResponse
import kotlinx.coroutines.*

class RemoteDatasourceImpl : RemoteDatasource {
    private val TAG: String = this::class.java.simpleName
    override suspend fun getCurrentWeather(): CurrentWeatherResponse {
        return try {
            Log.d(TAG, "getCurrentWeather: Remote")
            ApiService.create().getCurrentWeather(
                latitude = 51.5073,
                longitude = -0.1277,
                appid = "0b0f9f5b54968d5ad5c5788c304286f3",
                language = "ru",
                units = "metric"
            )
        } catch (e: Exception){
            Log.d(TAG, "getCurrentWeather: Error ${e.localizedMessage}")
            CurrentWeatherResponse(null, e.localizedMessage)
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