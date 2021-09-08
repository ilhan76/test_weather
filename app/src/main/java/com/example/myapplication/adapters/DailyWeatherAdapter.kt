package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.viewHolders.DailyWeatherViewHolder
import com.example.myapplication.data.domain.DailyWeatherItemDomain
import com.example.myapplication.databinding.ItemDailyWeatherBinding
import com.example.myapplication.util.RvDailyWeatherDelegate

class DailyWeatherAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: MutableList<DailyWeatherItemDomain> = ArrayList()
    private var delegate: RvDailyWeatherDelegate? = null

    fun setList(newList: List<DailyWeatherItemDomain>) {
        list.clear()
        list.addAll(newList)

        notifyDataSetChanged()
    }

    fun attachDelegate(newDelegate: RvDailyWeatherDelegate){
        delegate = newDelegate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DailyWeatherViewHolder(
            ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            delegate
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DailyWeatherViewHolder) holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}