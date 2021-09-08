package com.example.myapplication.util

import com.example.myapplication.data.domain.CurrentWeatherDomain
import com.example.myapplication.data.domain.DailyWeatherItemDomain
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.net.response.CurrentDto
import com.example.myapplication.net.response.DailyWeatherItemDto
import com.example.myapplication.net.response.HourlyWeatherItemDto
import java.util.*

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
    iconUrl = getImageUrl(weather.first().icon)
)

fun HourlyWeatherItemDto.toDomain() = HourlyWeatherItemDomain(
    time = unixToIso(dt).substring(11, 16),
    iconUrl = getImageUrl(weather.first().icon),
    temp = temp
)

fun DailyWeatherItemDto.toDomain() = DailyWeatherItemDomain(
    data = unixToIso(dt).substring(0, 10),
    minTemp = temp.min.toInt(),
    maxTemp = temp.max.toInt(),
    iconUrl = getImageUrl(weather.first().icon)
)

private fun getImageUrl(path: String): String {
    return "http://openweathermap.org/img/wn/${path}@2x.png"
}

private fun unixToIso(unixTime: Long): String {
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date = Date(unixTime * 1000)
    return sdf.format(date)
}