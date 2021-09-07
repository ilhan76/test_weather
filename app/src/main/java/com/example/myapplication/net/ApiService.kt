package com.example.myapplication.net

import com.example.myapplication.util.addJsonConvertor
import com.example.myapplication.util.setClient
import retrofit2.Retrofit

interface ApiService {
    companion object{
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(): ApiService =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .setClient()
                .addJsonConvertor()
                .build()
                .create(ApiService::class.java)
    }
}