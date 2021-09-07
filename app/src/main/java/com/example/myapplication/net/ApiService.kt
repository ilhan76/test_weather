package com.example.myapplication.net

import com.example.myapplication.net.response.CurrentWeatherResponse
import com.example.myapplication.net.response.HourlyListWeatherResponse
import com.example.myapplication.net.response.ListWeatherResponse
import com.example.myapplication.util.addJsonConvertor
import com.example.myapplication.util.setClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

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

    @GET("onecall")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("lang") language: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ) : CurrentWeatherResponse

    @GET("onecall")
    suspend fun getHourlyListWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("lang") language: String,
        @Query("appid") appid: String,
        @Query("units") units: String
    ) : HourlyListWeatherResponse

    @GET("onecall")
    suspend fun getDailyListWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("lang") language: String,
        @Query("appid") appid: String,
        @Query("metric") metric: String
    )
}