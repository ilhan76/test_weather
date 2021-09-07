package com.example.myapplication.screen.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.domain.WeatherItemDomain
import com.example.myapplication.data.source.impl.RemoteDatasourceImpl
import com.example.myapplication.repository.WeatherRepository
import com.example.myapplication.repository.WeatherRepositoryImpl
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

    private val _weatherLiveData = MutableLiveData<List<WeatherItemDomain>>()
    val weatherLiveData: LiveData<List<WeatherItemDomain>> = _weatherLiveData

    fun loadWeather(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.getFiveDayWeather()
                    .onEach {
                        if (it.content != null){
                            withContext(Dispatchers.Main){
                                _weatherLiveData.postValue(it.content!!)
                            }
                        } else{
                            Log.d(TAG, "loadWeather: Error ${it.message}")
                        }
                    }.collect()
            }
        }
    }
}