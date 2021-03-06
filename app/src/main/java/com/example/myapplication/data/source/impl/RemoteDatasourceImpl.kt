package com.example.myapplication.data.source.impl

import android.util.Log
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.source.RemoteDatasource
import com.example.myapplication.net.ApiService
import com.example.myapplication.net.response.*

class RemoteDatasourceImpl : RemoteDatasource {
    private val TAG: String = this::class.java.simpleName
    override suspend fun getCityName(latitude: Double, longitude: Double): CityNameResponse {
        return try {
            Log.d(TAG, "getCityName: Remote")
            ApiService.create().getCityName(
                latitude = latitude,
                longitude = longitude
            )
        } catch (e: Exception){
            Log.d(TAG, "getCityName: Error ${e.localizedMessage}")
            CityNameResponse(null, e.localizedMessage)
        }
    }

    override suspend fun getCityCoordinate(cityName: String): CityResponse {
        return try {
            Log.d(TAG, "getCityCoordinate: Remote")
            ApiService.create().getCityCoordinate(
                cityName = cityName
            )
        } catch (e: Exception) {
            Log.d(TAG, "getCityCoordinate: Error ${e.localizedMessage}")
            CityResponse(message = e.localizedMessage)
        }
    }

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): CurrentWeatherResponse {
        return try {
            Log.d(TAG, "getCurrentWeather: Remote")
            ApiService.create().getCurrentWeather(
                latitude = latitude,
                longitude = longitude
            )
        } catch (e: Exception) {
            Log.d(TAG, "getCurrentWeather: Error ${e.localizedMessage}")
            CurrentWeatherResponse(null, e.localizedMessage)
        }
    }

    override suspend fun getHourlyListWeather(
        latitude: Double,
        longitude: Double
    ): HourlyListWeatherResponse {
        return try {
            Log.d(TAG, "getHourlyListWeatherAsync: Remote")
            ApiService.create().getHourlyListWeather(
                latitude = latitude,
                longitude = longitude
            )
        } catch (e: Exception) {
            Log.d(TAG, "getHourlyListWeatherAsync: Error ${e.localizedMessage}")
            HourlyListWeatherResponse(null, e.localizedMessage)
        }
    }

    override suspend fun getDailyListWeather(
        latitude: Double,
        longitude: Double
    ): DailyListWeatherResponse {
        return try {
            Log.d(TAG, "getDailyListWeather: Remote")
            ApiService.create().getDailyListWeather(
                latitude = latitude,
                longitude = longitude
            )
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "getDailyListWeather: Error ${e.localizedMessage}")
            DailyListWeatherResponse(null, e.localizedMessage)
        }
    }

}