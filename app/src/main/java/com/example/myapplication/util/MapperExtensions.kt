package com.example.myapplication.util

import com.example.myapplication.data.domain.WeatherItemDomain
import com.example.myapplication.data.dto.WeatherItemDto

fun WeatherItemDto.toDomain() = WeatherItemDomain(
    tempMax = main.tempMax,
    tempMin = main.tempMin,
    pressure = main.pressure,
    humidity = main.humidity,
    mainWeather = weather.main,
    weatherDescription = weather.description,
    iconUrl = "http://openweathermap.org/img/wn/${weather.icon}@2x.png",
    cloudiness = clouds.all,
    speed = wind.speed,
    deg = wind.deg,
    probabilityOfPrecipitation = pop,
    rainVolume = rain?.rainVolume,
    snowVolume = snow?.snowVolume,
    partOfDay = sys.pod
)