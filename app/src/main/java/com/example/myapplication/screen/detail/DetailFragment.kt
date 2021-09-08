package com.example.myapplication.screen.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.domain.DailyWeatherItemDomain
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.FragmentHomeBinding

const val ARG_DETAIL_WEATHER = "day_weather"

class DetailFragment : Fragment() {
    private val TAG: String = this::class.java.simpleName
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val weather = arguments?.getSerializable(ARG_DETAIL_WEATHER) as DailyWeatherItemDomain

        binding.apply {
            txtDate.text = weather.date

            Glide.with(requireContext())
                .load(weather.iconUrl)
                .into(mainWeatherIcon)

            txtMain.text = weather.main
            txtDescription.text = weather.description
            txtTemp.text = context?.getString(
                R.string.daily_temp_pattern,
                weather.tempMax,
                weather.tempMin
            )

            txtWind.text = context?.getString(
                R.string.wind_pattern,
                weather.windSpeed
            )
            txtHumidity.text = context?.getString(
                R.string.humidity_pattern,
                weather.humidity
            )
            txtDewPoint.text = context?.getString(
                R.string.dew_point_pattern,
                weather.dewPoint
            )
            txtPressure.text = context?.getString(
                R.string.pressure_pattern,
                weather.pressure.toString()
            )
            txtPop.text = context?.getString(
                R.string.pop_pattern,
                weather.pop.toString()
            )
            txtClouds.text = context?.getString(
                R.string.clouds_pattern,
                weather.clouds.toString()
            )

            txtTempMorn.text = context?.getString(
                R.string.morning_temp_pattern,
                weather.tempMorn
            )
            txtTempDay.text = context?.getString(
                R.string.day_temp_pattern,
                weather.tempDay
            )
            txtTempEve.text = context?.getString(
                R.string.evening_temp_pattern,
                weather.tempEve
            )
            txtTempNight.text = context?.getString(
                R.string.night_temp_pattern,
                weather.tempNight
            )

            if (weather.rain != null || weather.snow != null) {
                txtPopTitle.isVisible = true
                txtRainPrecipitation.isVisible = true
                txtSnowPrecipitation.isVisible = true

                if (weather.rain != null)
                    txtRainPrecipitation.text = context?.getString(
                        R.string.rain_pattern,
                        weather.rain.toString()
                    )
                if (weather.snow != null)
                    txtSnowPrecipitation.text = context?.getString(
                        R.string.snow_pattern,
                        weather.snow.toString()
                    )
            } else {
                txtPopTitle.isVisible = false
                txtRainPrecipitation.isVisible = false
                txtSnowPrecipitation.isVisible = false
            }
        }
    }
}