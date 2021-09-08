package com.example.myapplication.screen.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.domain.CurrentWeatherDomain
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.data.source.impl.RemoteDatasourceImpl
import com.example.myapplication.repository.WeatherRepository
import com.example.myapplication.repository.impl.WeatherRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val context: Application): AndroidViewModel(context) {
    private val TAG: String = this::class.java.simpleName
    private val repository: WeatherRepository = WeatherRepositoryImpl(
        remoteDatasource = RemoteDatasourceImpl()
    )

    private val _hourlyWeatherLiveData = MutableLiveData<List<HourlyWeatherItemDomain>>()
    val hourlyWeatherLiveData: LiveData<List<HourlyWeatherItemDomain>> = _hourlyWeatherLiveData

    private val _currentWeatherLiveData = MutableLiveData<CurrentWeatherDomain>()
    val currentWeatherLiveData: LiveData<CurrentWeatherDomain> = _currentWeatherLiveData

    fun loadCurrentWeather(latitude: Double, longitude: Double){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.getCurrentWeather(latitude, longitude)
                    .onEach {
                        if (it.content != null){
                            withContext(Dispatchers.Main){
                                _currentWeatherLiveData.postValue(it.content!!)
                            }
                        } else {
                            Log.d(TAG, "loadCurrentWeather: Error")
                        }
                    }.collect()
            }
        }
    }

    fun loadHourlyWeather(latitude: Double, longitude: Double){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.getHourlyListWeather(latitude, longitude)
                    .onEach {
                        if (it.content != null){
                            Log.d(TAG, "loadHourlyWeather: ${it.content}")
                            withContext(Dispatchers.Main){
                                _hourlyWeatherLiveData.postValue(it.content!!)
                            }
                        } else{
                            Log.d(TAG, "loadHourlyWeather: Error ${it.message}")
                        }
                    }.collect()
            }
        }
    }
}