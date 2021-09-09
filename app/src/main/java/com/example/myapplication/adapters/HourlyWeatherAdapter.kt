package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.viewHolders.HourlyWeatherViewHolder
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.databinding.ItemHourlyWeatherBinding


class HourlyWeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: MutableList<HourlyWeatherItemDomain> = ArrayList()

    fun setList(newList: List<HourlyWeatherItemDomain>) {
        list.clear()
        list.addAll(newList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HourlyWeatherViewHolder(
            ItemHourlyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HourlyWeatherViewHolder) holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}