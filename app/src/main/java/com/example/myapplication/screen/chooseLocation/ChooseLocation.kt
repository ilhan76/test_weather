package com.example.myapplication.screen.chooseLocation

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.Gravity
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
import com.example.myapplication.data.domain.CityDomain
import com.example.myapplication.databinding.FragmentChooseLocationBinding
import com.example.myapplication.screen.home.FeatureState
import com.example.myapplication.util.*
import com.example.myapplication.util.PREF_ARG_LAT

class ChooseLocation : Fragment() {
    private val TAG: String = this::class.java.simpleName
    private var _binding: FragmentChooseLocationBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LocationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLocationBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init(){
        viewModel.cityLiveData.observe(viewLifecycleOwner, this::render)
        initListeners()
    }

    private fun initListeners(){
        binding.btnSearch.setOnClickListener {
            if (binding.etxtSearch.text.toString().trim().isNotEmpty()){
                viewModel.findCity(binding.etxtSearch.text.toString())
            } else {
                Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).apply {
                    setGravity(Gravity.CENTER, 0, 0)
                    show()
                }
            }
        }

        binding.cityCard.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(GEO_FLAG, FLAG_CITY)
            findNavController().navigate(R.id.action_chooseLocation_to_homeFragment, bundle)
        }

        binding.btnUseGeolocation.setOnClickListener {
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
                val bundle = Bundle()
                bundle.putString(GEO_FLAG, FLAG_GEOLOCATION)
                findNavController().navigate(R.id.action_chooseLocation_to_homeFragment, bundle)
            }
        }
    }

    private fun render(state: FeatureState) {
        when(state){
            FeatureState.Default -> {
                binding.progressBar.isVisible = false
                binding.txtError.isVisible = false
                binding.cityCard.isVisible = false
            }
            is FeatureState.Error -> {
                binding.progressBar.isVisible = false
                binding.txtError.isVisible = true
                binding.cityCard.isVisible = false
            }
            FeatureState.Loading -> {
                binding.progressBar.isVisible = true
                binding.txtError.isVisible = false
                binding.cityCard.isVisible = false
            }
            is FeatureState.Success<*> -> {
                val cityDomain = state.content as CityDomain
                binding.apply {
                    txtMain.text = cityDomain.main
                    txtDescription.text = cityDomain.description
                    txtCityName.text = cityDomain.name

                    Glide.with(requireContext())
                        .load(cityDomain.icon)
                        .into(icon)

                    cityCard.isVisible = true
                }
                val pref: SharedPreferences = requireActivity().getSharedPreferences(FILE_PREF_NAME, Context.MODE_PRIVATE)
                val editor = pref.edit()

                editor.apply {
                    putFloat(PREF_ARG_LON, cityDomain.lon.toFloat())
                    putFloat(PREF_ARG_LAT, cityDomain.lat.toFloat())
                }
                editor.apply()
                binding.progressBar.isVisible = false
            }
        }
    }
}