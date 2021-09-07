package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.data.source.impl.RemoteDatasourceImpl
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.simpleName
    private lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")

        //navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}