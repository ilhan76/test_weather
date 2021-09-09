package com.example.myapplication.net

import com.example.myapplication.net.response.*
import com.example.myapplication.util.extensions.addJsonConvertor
import com.example.myapplication.util.extensions.setClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun create(): ApiService =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .setClient()
                .addJsonConvertor()
                .build()
                .create(ApiService::class.java)
    }

    @GET("weather")
    suspend fun getCityCoordinate(
        @Query("q") cityName: String
    ): CityResponse

    @GET("weather")
    suspend fun getCityName(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): CityNameResponse

    @GET("onecall?exclude=minutely,hourly,daily,alerts")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): CurrentWeatherResponse

    @GET("onecall?exclude=current,minutely,daily,alerts")
    suspend fun getHourlyListWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): HourlyListWeatherResponse

    @GET("onecall?exclude=current,minutely,hourly,alerts")
    suspend fun getDailyListWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): DailyListWeatherResponse
}