package com.example.myapplication.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.databinding.ItemHourlyWeatherBinding

class HourlyWeatherViewHolder(private val binding: ItemHourlyWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(hourlyWeather: HourlyWeatherItemDomain) {
        binding.temp.text = hourlyWeather.temp.toInt().toString() + "° C"
        binding.time.text = hourlyWeather.time

        Glide.with(binding.root.context)
            .load(hourlyWeather.iconUrl)
            .into(binding.icon)
    }
}