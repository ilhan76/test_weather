package com.example.myapplication.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.domain.DailyWeatherItemDomain
import com.example.myapplication.databinding.ItemDailyWeatherBinding

class DailyWeatherViewHolder(private val binding: ItemDailyWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DailyWeatherItemDomain) {
        binding.apply {
            txtDate.text = item.data
            txtTemp.text = binding.root.context.getString(
                R.string.daily_temp_pattern,
                item.maxTemp,
                item.minTemp
            )
            Glide.with(binding.root.context)
                .load(item.iconUrl)
                .into(icon)
        }
    }
}