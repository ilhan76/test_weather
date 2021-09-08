package com.example.myapplication.util

import com.example.myapplication.data.domain.CurrentWeatherDomain
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.net.response.CurrentDto
import com.example.myapplication.net.response.HourlyWeatherItemDto
import java.util.*

fun HourlyWeatherItemDto.toDomain() = HourlyWeatherItemDomain(
    time = unixToIso(dt),
    iconUrl = "http://openweathermap.org/img/wn/${weather.first().icon}@2x.png",
    temp = temp
)

fun CurrentDto.toDomain() = CurrentWeatherDomain(
    temp = temp.toInt(),
    feelsLike = feelsLike.toInt(),
    windSpeed = windSpeed.toInt(),
    humidity = humidity,
    uvi = uvi,
    pressure = pressure,
    visibility = visibility,
    main = weather.first().main,
    description = weather.first().description,
    iconUrl = "http://openweathermap.org/img/wn/${weather.first().icon}@2x.png"
)

private fun unixToIso(unixTime: Long): String {
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date = Date(unixTime * 1000)
    val iso = sdf.format(date)
    return iso.substring(11, 16)
}