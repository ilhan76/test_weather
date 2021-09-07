package com.example.myapplication.util

import com.example.myapplication.data.domain.WeatherItemDomain
import com.example.myapplication.data.dto.WeatherItemDto

fun WeatherItemDto.toDomain() = WeatherItemDomain(
    tempMax = main.tempMax,
    tempMin = main.tempMin,
    pressure = main.pressure,
    humidity = main.humidity,
    mainWeather = weather.first().main,
    weatherDescription = weather.first().description,
    iconUrl = "http://openweathermap.org/img/wn/${weather.first().icon}@2x.png",
    cloudiness = clouds.all,
    speed = wind.speed,
    deg = wind.deg,
    probabilityOfPrecipitation = pop,
    rainVolume = rain?.rainVolume,
    snowVolume = snow?.snowVolume,
    partOfDay = sys.pod
)