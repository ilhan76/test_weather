package com.example.myapplication.screen.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.myapplication.adapters.HourlyWeatherAdapter
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.data.domain.WeatherItemDomain
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

    private fun init(){
        Log.d(TAG, "init: Init")

        binding.rvCurrentDay.adapter = hourlyAdapter

        viewModel.hourlyWeatherLiveData.observe(viewLifecycleOwner, this::render)
        viewModel.loadHourlyWeather()
    }

    private fun render(weatherItemsDomain: List<HourlyWeatherItemDomain>){
        hourlyAdapter.setList(weatherItemsDomain)
        Toast.makeText(requireContext(), "Gjkexbkb", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}