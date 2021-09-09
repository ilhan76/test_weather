package com.example.myapplication.adapters.delegates

import com.example.myapplication.data.domain.DailyWeatherItemDomain

interface RvDailyWeatherDelegate {
    fun toDetail(weatherItemDomain: DailyWeatherItemDomain?)
}