package com.example.myapplication.screen.home

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapters.HourlyWeatherAdapter
import com.example.myapplication.data.domain.CurrentWeatherDomain
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlin.math.log

class HomeFragment : Fragment() {
    private val TAG: String = this::class.java.simpleName
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private val hourlyAdapter: HourlyWeatherAdapter = HourlyWeatherAdapter()

    private var locationManager: LocationManager? = null
    private val locationListener: LocationListener = LocationListener {
        viewModel.loadCurrentWeather(it.latitude, it.longitude)
        viewModel.loadHourlyWeather(it.latitude, it.longitude)
    }

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

       locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            ActivityCompat.requestPermissions(requireActivity(), permissions, 0)
        } else {
            Log.d(TAG, "init: Success")
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                10000f,
                locationListener
            )
        }

        binding.rvCurrentDay.adapter = hourlyAdapter

        viewModel.hourlyWeatherLiveData.observe(viewLifecycleOwner, this::renderHourlyWeather)
        viewModel.currentWeatherLiveData.observe(viewLifecycleOwner, this::renderCurrentWeather)
        //viewModel.loadCurrentWeather()
        //viewModel.loadHourlyWeather()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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
            txtFeelsLike.text = context?.getString(
                R.string.feels_like_pattern,
                currentWeather.feelsLike.toString()
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}