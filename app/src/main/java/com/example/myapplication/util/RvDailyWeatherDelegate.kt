package com.example.myapplication.util

import com.example.myapplication.data.domain.DailyWeatherItemDomain

interface RvDailyWeatherDelegate {
    fun toDetail(weatherItemDomain: DailyWeatherItemDomain?)
}