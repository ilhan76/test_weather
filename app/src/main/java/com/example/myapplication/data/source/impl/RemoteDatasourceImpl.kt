package com.example.myapplication.data.source.impl

import android.util.Log
import com.example.myapplication.data.source.RemoteDatasource
import com.example.myapplication.net.ApiService
import com.example.myapplication.net.response.ListWeatherResponse
import kotlinx.coroutines.*

class RemoteDatasourceImpl: RemoteDatasource {
    private val TAG: String = this::class.java.simpleName

    override suspend fun getWeatherListAsync(): Deferred<ListWeatherResponse> =
        GlobalScope.async(Dispatchers.IO){
            try {
                Log.d(TAG, "getWeatherListAsync: Remote")
                return@async ApiService.create().getListWeather(
                    latitude = 51.5073, longitude = -0.1277, appid = "0b0f9f5b54968d5ad5c5788c304286f3", language = "ru"
                )
            } catch (e: Exception){
                Log.d(TAG, "getWeatherListAsync: ${e.localizedMessage}")
                return@async ListWeatherResponse(-1, e.localizedMessage, null, null)
            }
        }
}