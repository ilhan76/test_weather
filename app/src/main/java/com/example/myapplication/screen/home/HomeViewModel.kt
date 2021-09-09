package com.example.myapplication.screen.home

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
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
import com.example.myapplication.util.extensions.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class FeatureState {
    object Loading : FeatureState()
    object Default : FeatureState()
    class Success<T>(val content: T) : FeatureState()
    class Error(val error: String?) : FeatureState()
}

class HomeViewModel(private val context: Application) : AndroidViewModel(context) {
    private val TAG: String = this::class.java.simpleName
    private val repository: WeatherRepository = WeatherRepositoryImpl(
        remoteDatasource = RemoteDatasourceImpl()
    )
    private val pref: SharedPreferences =
        context.getSharedPreferences(FILE_PREF_NAME, Context.MODE_PRIVATE)

    private val _cityNameLiveData =
        MutableLiveData<FeatureState>().default(initialValue = FeatureState.Default)
    val cityNameLiveData: LiveData<FeatureState> = _cityNameLiveData

    private val _currentWeatherLiveData =
        MutableLiveData<FeatureState>().default(initialValue = FeatureState.Default)
    val currentWeatherLiveData: LiveData<FeatureState> = _currentWeatherLiveData

    private val _hourlyWeatherLiveData =
        MutableLiveData<FeatureState>().default(initialValue = FeatureState.Default)
    val hourlyWeatherLiveData: LiveData<FeatureState> = _hourlyWeatherLiveData

    private val _dailyWeatherLiveData =
        MutableLiveData<FeatureState>().default(initialValue = FeatureState.Default)
    val dailyWeatherLiveData: LiveData<FeatureState> = _dailyWeatherLiveData

    fun updatePage(){
        _cityNameLiveData.postValue(FeatureState.Default)
        _currentWeatherLiveData.postValue(FeatureState.Default)
        _hourlyWeatherLiveData.postValue(FeatureState.Default)
        _dailyWeatherLiveData.postValue(FeatureState.Default)
    }

    fun loadCityName(flag: String) {
        Toast.makeText(context,
            "${if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LAT, 0f)
            .toDouble() else pref.getFloat(PREF_ARG_LAT_GEO, 0f).toDouble()}" +
                    "\n" +
                    "${if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LON, 0f).toDouble() else pref.getFloat(PREF_ARG_LON_GEO, 0f).toDouble()}",
            Toast.LENGTH_SHORT).show()

        _cityNameLiveData.postValue(FeatureState.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getCityName(
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LAT, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LAT_GEO, 0f).toDouble(),
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LON, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LON_GEO, 0f).toDouble()
                ).onEach {
                    if (it.content != null) {
                        withContext(Dispatchers.Main) {
                            _cityNameLiveData.postValue(FeatureState.Success(it.content))
                        }
                    } else {
                        Log.d(TAG, "loadCityName: Error ${it.message}")
                    }
                }.collect()
            }
        }
    }

    fun loadCurrentWeather(flag: String) {
        _currentWeatherLiveData.postValue(FeatureState.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getCurrentWeather(
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LAT, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LAT_GEO, 0f).toDouble(),
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LON, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LON_GEO, 0f).toDouble()
                ).onEach {
                    if (it.content != null) {
                        withContext(Dispatchers.Main) {
                            _currentWeatherLiveData.postValue(FeatureState.Success(it.content))
                        }
                    } else {
                        Log.d(TAG, "loadCurrentWeather: Error")
                    }
                }.collect()
            }
        }
    }

    fun loadHourlyWeather(flag: String) {
        _hourlyWeatherLiveData.postValue(FeatureState.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getHourlyListWeather(
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LAT, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LAT_GEO, 0f).toDouble(),
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LON, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LON_GEO, 0f).toDouble()
                ).onEach {
                    if (it.content != null) {
                        Log.d(TAG, "loadHourlyWeather: ${it.content}")
                        withContext(Dispatchers.Main) {
                            _hourlyWeatherLiveData.postValue(FeatureState.Success(it.content))
                        }
                    } else {
                        Log.d(TAG, "loadHourlyWeather: Error ${it.message}")
                        withContext(Dispatchers.Main) {
                            _hourlyWeatherLiveData.postValue(FeatureState.Error(it.message))
                        }
                    }
                }.collect()
            }
        }
    }

    fun loadDailyWeather(flag: String) {
        _dailyWeatherLiveData.postValue(FeatureState.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getDailyListWeather(
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LAT, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LAT_GEO, 0f).toDouble(),
                    if (flag == FLAG_CITY) pref.getFloat(PREF_ARG_LON, 0f)
                        .toDouble() else pref.getFloat(PREF_ARG_LON_GEO, 0f).toDouble()
                ).onEach {
                    if (it.content != null) {
                        Log.d(TAG, "loadDailyWeather: ${it.content}")
                        _dailyWeatherLiveData.postValue(FeatureState.Success(it.content))
                    } else {
                        Log.d(TAG, "loadDailyWeather: Error ${it.message}")
                    }
                }.collect()
            }
        }
    }
}