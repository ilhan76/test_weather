package com.example.myapplication.screen.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapters.HourlyWeatherAdapter
import com.example.myapplication.data.domain.CurrentWeatherDomain
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val TAG: String = this::class.java.simpleName
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private val hourlyAdapter: HourlyWeatherAdapter = HourlyWeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        Log.d(TAG, "init: Init")

        binding.rvCurrentDay.adapter = hourlyAdapter

        viewModel.hourlyWeatherLiveData.observe(viewLifecycleOwner, this::renderHourlyWeather)
        viewModel.currentWeatherLiveData.observe(viewLifecycleOwner, this::renderCurrentWeather)
        viewModel.loadCurrentWeather()
        viewModel.loadHourlyWeather()
    }

    private fun renderCurrentWeather(currentWeather: CurrentWeatherDomain) {
        binding.apply {
            Glide.with(requireContext())
                .load(currentWeather.iconUrl)
                .into(mainWeatherIcon)

            txtTemp.text = context?.getString(
                R.string.degr_pattern,
                currentWeather.temp
            )
            txtMain.text = currentWeather.main
            txtDescription.text = currentWeather.description

            txtWind.text = context?.getString(
                R.string.wind_pattern,
                currentWeather.windSpeed
            )
            txtHumidity.text = context?.getString(
                R.string.humidity_pattern,
                currentWeather.humidity
            )
            txtUvIndex.text = context?.getString(
                R.string.uv_index_pattern,
                currentWeather.uvi.toString()
            )
            txtPressure.text = context?.getString(
                R.string.pressure_pattern,
                currentWeather.pressure.toString()
            )
            txtVisibility.text = context?.getString(
                R.string.visibility_pattern,
                currentWeather.visibility.toString()
            )
        }
    }

    private fun renderHourlyWeather(weatherItemsDomain: List<HourlyWeatherItemDomain>) {
        hourlyAdapter.setList(weatherItemsDomain)
        Toast.makeText(requireContext(), "Gjkexbkb", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}