package com.example.myapplication.screen.chooseLocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentChooseLocationBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.screen.home.HomeViewModel

class ChooseLocation : Fragment() {
    private val TAG: String = this::class.java.simpleName
    private var _binding: FragmentChooseLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLocationBinding.inflate(inflater, container, false)
        return binding.root
    }
}