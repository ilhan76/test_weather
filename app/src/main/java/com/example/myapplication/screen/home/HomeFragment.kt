package com.example.myapplication.screen.home

import android.Manifest
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.adapters.DailyWeatherAdapter
import com.example.myapplication.adapters.HourlyWeatherAdapter
import com.example.myapplication.adapters.delegates.RvDailyWeatherDelegate
import com.example.myapplication.data.domain.CurrentWeatherDomain
import com.example.myapplication.data.domain.DailyWeatherItemDomain
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.screen.detail.ARG_DETAIL_WEATHER
import com.example.myapplication.util.*

class HomeFragment : Fragment(), RvDailyWeatherDelegate {
    private val TAG: String = this::class.java.simpleName
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private val hourlyAdapter: HourlyWeatherAdapter = HourlyWeatherAdapter()
    private val dailyAdapter: DailyWeatherAdapter = DailyWeatherAdapter()

    private var locationManager: LocationManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        init()
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        binding.apply {
            shimmerTxtCityName.stopShimmerAnimation()
            shimmerMainWeatherIcon.stopShimmerAnimation()
            shimmerTxtMain.stopShimmerAnimation()
            shimmerTxtDescription.stopShimmerAnimation()
            shimmerTxtTemp.stopShimmerAnimation()
            shimmerTxtFeelsLike.stopShimmerAnimation()
            shimmerRvCurrentDay.stopShimmerAnimation()
            shimmerRvNextDays.stopShimmerAnimation()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager = null
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager
        checkSelfPermission()
    }

    private fun init() {
        Log.d(TAG, "init: Init")

        binding.rvCurrentDay.adapter = hourlyAdapter
        dailyAdapter.attachDelegate(this)
        binding.rvNextDays.adapter = dailyAdapter

        viewModel.cityNameLiveData.observe(viewLifecycleOwner, this::renderCityName)
        viewModel.currentWeatherLiveData.observe(viewLifecycleOwner, this::renderCurrentWeather)
        viewModel.hourlyWeatherLiveData.observe(viewLifecycleOwner, this::renderHourlyWeather)
        viewModel.dailyWeatherLiveData.observe(viewLifecycleOwner, this::renderDailyWeather)

        initListeners()
    }

    private fun initListeners() {
        binding.btnChangeLocation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chooseLocation)
        }
    }

    private fun checkSelfPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager?.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                10000f
            ) {
                val pref: SharedPreferences =
                    requireActivity().getSharedPreferences(FILE_PREF_NAME, Context.MODE_PRIVATE)
                val editor = pref.edit()
                editor.apply {
                    putFloat(PREF_ARG_LAT_GEO, it.longitude.toFloat())
                    putFloat(PREF_ARG_LAT_GEO, it.latitude.toFloat())
                }
                editor.apply()
                viewModel.updatePage()
            }
        }
    }

    private fun renderCityName(state: FeatureState) {
        when(state){
            FeatureState.Default -> {
                val flag = arguments?.getString(GEO_FLAG) as String
                viewModel.loadCityName(flag)
            }
            is FeatureState.Error -> {
                Toast.makeText(context, "Error ${state.error}", Toast.LENGTH_SHORT).show()
            }
            FeatureState.Loading -> {
                binding.shimmerTxtCityName.startShimmerAnimation()
            }
            is FeatureState.Success<*> -> {
                binding.apply {
                    txtCityName.text = state.content as String
                    shimmerTxtCityName.stopShimmerAnimation()
                    shimmerTxtCityName.isVisible = false
                    txtCityName.isVisible = true
                }
            }
        }
    }

    private fun renderCurrentWeather(state: FeatureState) {
        when (state) {
            FeatureState.Default -> {
                val flag = arguments?.getString(GEO_FLAG) as String
                viewModel.loadCurrentWeather(flag)
            }
            is FeatureState.Error -> {
                Toast.makeText(context, "Error ${state.error}", Toast.LENGTH_SHORT).show()
            }
            FeatureState.Loading -> {
                binding.apply {
                    shimmerMainWeatherIcon.startShimmerAnimation()
                    shimmerTxtMain.startShimmerAnimation()
                    shimmerTxtDescription.startShimmerAnimation()
                    shimmerTxtTemp.startShimmerAnimation()
                    shimmerTxtFeelsLike.startShimmerAnimation()
                }
            }
            is FeatureState.Success<*> -> {
                binding.apply {
                    val currentWeather = state.content as CurrentWeatherDomain
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

                    hideShimmerCurrentWeather()
                }
            }
        }
    }

    private fun hideShimmerCurrentWeather() {
        binding.apply {
            shimmerMainWeatherIcon.isVisible = false
            shimmerTxtTemp.isVisible = false
            shimmerTxtFeelsLike.isVisible = false
            shimmerTxtMain.isVisible = false
            shimmerTxtDescription.isVisible = false

            mainWeatherIcon.isVisible = true
            txtTemp.isVisible = true
            txtFeelsLike.isVisible = true
            txtMain.isVisible = true
            txtDescription.isVisible = true
        }
    }

    private fun renderHourlyWeather(state: FeatureState) = when (state) {
        FeatureState.Default -> {
            val flag = arguments?.getString(GEO_FLAG) as String
            viewModel.loadHourlyWeather(flag)
        }
        is FeatureState.Error -> {
            Toast.makeText(context, "Error ${state.error}", Toast.LENGTH_SHORT).show()
        }
        FeatureState.Loading -> {
            binding.shimmerRvCurrentDay.startShimmerAnimation()
            binding.shimmerRvCurrentDay.isVisible = true
            binding.rvCurrentDay.isVisible = false
        }
        is FeatureState.Success<*> -> {
            binding.shimmerRvCurrentDay.stopShimmerAnimation()
            binding.shimmerRvCurrentDay.isVisible = false
            binding.rvCurrentDay.isVisible = true
            hourlyAdapter.setList(state.content as List<HourlyWeatherItemDomain>)
        }
    }

    private fun renderDailyWeather(state: FeatureState) = when (state) {
        FeatureState.Default -> {
            val flag = arguments?.getString(GEO_FLAG) as String
            viewModel.loadDailyWeather(flag)
        }
        is FeatureState.Error -> {
            Toast.makeText(context, "Error ${state.error}", Toast.LENGTH_SHORT).show()
        }
        FeatureState.Loading -> {
            binding.shimmerRvNextDays.startShimmerAnimation()
            binding.shimmerRvNextDays.isVisible = true
            binding.rvNextDays.isVisible = false
        }
        is FeatureState.Success<*> -> {
            binding.shimmerRvNextDays.stopShimmerAnimation()
            binding.shimmerRvNextDays.isVisible = false
            binding.rvNextDays.isVisible = true
            dailyAdapter.setList(state.content as List<DailyWeatherItemDomain>)
        }
    }

    override fun toDetail(weatherItemDomain: DailyWeatherItemDomain?) {
        val bundle = Bundle()
        bundle.putSerializable(ARG_DETAIL_WEATHER, weatherItemDomain)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}