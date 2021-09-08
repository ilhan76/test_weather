package com.example.myapplication.screen.chooseLocation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplication.data.domain.CityDomain
import com.example.myapplication.databinding.FragmentChooseLocationBinding
import com.example.myapplication.util.*
import com.example.myapplication.util.PREF_ARG_LAT

class ChooseLocation : Fragment() {
    private val TAG: String = this::class.java.simpleName
    private var _binding: FragmentChooseLocationBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<LocationViewModel>()

    private var appNavigation: AppNavigation? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLocationBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init(){
        appNavigation = requireActivity() as AppNavigation

        viewModel.cityLiveData.observe(viewLifecycleOwner, this::render)

        binding.btnSearch.setOnClickListener {
            if (binding.etxtSearch.text.toString().trim().isNotEmpty()){
                viewModel.findCity(binding.etxtSearch.text.toString())
            } else {
                Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        binding.cityCard.setOnClickListener {
            val pref: SharedPreferences = requireActivity().getSharedPreferences(FILE_PREF_NAME, Context.MODE_PRIVATE)
            val editor = pref.edit()

            editor.apply {
                putString(PREF_ARG_FLAG, FLAG_CITY)
            }
            editor.apply()

            appNavigation?.toHome()
        }
    }

    private fun render(cityDomain: CityDomain) {
        Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
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
    }
}