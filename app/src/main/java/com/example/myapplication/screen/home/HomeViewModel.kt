package com.example.myapplication.screen.home

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.domain.CurrentWeatherDomain
import com.example.myapplication.data.domain.DailyWeatherItemDomain
import com.example.myapplication.data.domain.HourlyWeatherItemDomain
import com.example.myapplication.data.source.impl.RemoteDatasourceImpl
import com.example.myapplication.repository.WeatherRepository
import com.example.myapplication.repository.impl.WeatherRepositoryImpl
import com.example.myapplication.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val context: Application) : AndroidViewModel(context) {
    private val TAG: String = this::class.java.simpleName
    private val repository: WeatherRepository = WeatherRepositoryImpl(
        remoteDatasource = RemoteDatasourceImpl()
    )
    private val pref: SharedPreferences =
        context.getSharedPreferences(FILE_PREF_NAME, Context.MODE_PRIVATE)

    private val _cityNameLiveData = MutableLiveData<String>()
    val cityNameLiveData: LiveData<String> = _cityNameLiveData

    private val _currentWeatherLiveData = MutableLiveData<CurrentWeatherDomain>()
    val currentWeatherLiveData: LiveData<CurrentWeatherDomain> = _currentWeatherLiveData

    private val _hourlyWeatherLiveData = MutableLiveData<List<HourlyWeatherItemDomain>>()
    val hourlyWeatherLiveData: LiveData<List<HourlyWeatherItemDomain>> = _hourlyWeatherLiveData

    private val _dailyWeatherLiveData = MutableLiveData<List<DailyWeatherItemDomain>>()
    val dailyWeatherLiveData: LiveData<List<DailyWeatherItemDomain>> = _dailyWeatherLiveData

    fun loadCityName(flag: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getCityName(
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LAT, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LAT, 0f).toDouble(),
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LON, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LON_GEO, 0f).toDouble()
                ).onEach {
                    if (it.content != null){
                        withContext(Dispatchers.Main){
                            _cityNameLiveData.postValue(it.content!!)
                        }
                    } else {
                        Log.d(TAG, "loadCityName: Error ${it.message}")
                    }
                }.collect()
            }
        }
    }

    fun loadCurrentWeather(flag: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getCurrentWeather(
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LAT, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LAT, 0f).toDouble(),
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LON, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LON_GEO, 0f).toDouble()
                ).onEach {
                    if (it.content != null) {
                        withContext(Dispatchers.Main) {
                            _currentWeatherLiveData.postValue(it.content!!)
                        }
                    } else {
                        Log.d(TAG, "loadCurrentWeather: Error")
                    }
                }.collect()
            }
        }
    }

    fun loadHourlyWeather(flag: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getHourlyListWeather(
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LAT, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LAT, 0f).toDouble(),
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LON, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LON_GEO, 0f).toDouble()
                )
                    .onEach {
                        if (it.content != null) {
                            Log.d(TAG, "loadHourlyWeather: ${it.content}")
                            withContext(Dispatchers.Main) {
                                _hourlyWeatherLiveData.postValue(it.content!!)
                            }
                        } else {
                            Log.d(TAG, "loadHourlyWeather: Error ${it.message}")
                        }
                    }.collect()
            }
        }
    }

    fun loadDailyWeather(flag: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getDailyListWeather(
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LAT, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LAT, 0f).toDouble(),
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LON, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LON_GEO, 0f).toDouble()
                )
                    .onEach {
                        if (it.content != null) {
                            Log.d(TAG, "loadDailyWeather: ${it.content}")
                            _dailyWeatherLiveData.postValue(it.content!!)
                        } else {
                            Log.d(TAG, "loadDailyWeather: Error ${it.message}")
                        }
                    }.collect()
            }
        }
    }
}