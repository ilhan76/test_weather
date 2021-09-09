package com.example.myapplication.screen.chooseLocation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.domain.CityDomain
import com.example.myapplication.data.source.impl.RemoteDatasourceImpl
import com.example.myapplication.repository.WeatherRepository
import com.example.myapplication.repository.impl.WeatherRepositoryImpl
import com.example.myapplication.screen.home.FeatureState
import com.example.myapplication.util.extensions.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocationViewModel(val context: Application) : AndroidViewModel(context) {
    private val TAG: String = this::class.java.simpleName
    private val repository: WeatherRepository = WeatherRepositoryImpl(
        remoteDatasource = RemoteDatasourceImpl()
    )

    private val _cityLiveData = MutableLiveData<FeatureState>().default(
        initialValue = FeatureState.Default
    )
    val cityLiveData: LiveData<FeatureState> = _cityLiveData

    fun findCity(string: String) {
        _cityLiveData.postValue(FeatureState.Loading)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getCityCoordinate(string)
                    .onEach {
                        if (it.content != null) {
                            withContext(Dispatchers.Main) {
                                _cityLiveData.postValue(FeatureState.Success(it.content))
                            }
                        } else {
                            Log.d(TAG, "findCity: Error ${it.message}")
                            withContext(Dispatchers.Main) {
                                _cityLiveData.postValue(FeatureState.Error(it.message))
                            }
                        }
                    }.collect()
            }
        }
    }

}