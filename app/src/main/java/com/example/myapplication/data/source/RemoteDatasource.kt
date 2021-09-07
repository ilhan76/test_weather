package com.example.myapplication.data.source

import com.example.myapplication.net.response.ListWeatherResponse
import kotlinx.coroutines.Deferred

interface RemoteDatasource {
    suspend fun getWeatherListAsync(): Deferred<ListWeatherResponse>
}