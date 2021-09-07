package com.example.myapplication.data.source.impl

import com.example.myapplication.data.source.RemoteDatasource
import com.example.myapplication.net.response.ListWeatherResponse
import kotlinx.coroutines.Deferred

class RemoteDatasourceImpl: RemoteDatasource {
    override suspend fun getWeatherListAsync(): Deferred<ListWeatherResponse> {
        TODO("Not yet implemented")
    }
}