package com.example.myapplication.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.domain.DailyWeatherItemDomain
import com.example.myapplication.databinding.ItemDailyWeatherBinding
import com.example.myapplication.adapters.delegates.RvDailyWeatherDelegate

class DailyWeatherViewHolder(
    private val binding: ItemDailyWeatherBinding,
    private val delegate: RvDailyWeatherDelegate?
) :
    RecyclerView.ViewHolder(binding.root) {

    private var item: DailyWeatherItemDomain? = null

    init {
        binding.ibtnToDetail.setOnClickListener {
            delegate?.toDetail(item)
        }
    }

    fun bind(item: DailyWeatherItemDomain) {
        this.item = item
        binding.apply {
            txtDate.text = item.date
            txtTemp.text = binding.root.context.getString(
                R.string.daily_temp_pattern,
                item.tempMax,
                item.tempMin
            )
            Glide.with(binding.root.context)
                .load(item.iconUrl)
                .into(icon)
        }
    }
}