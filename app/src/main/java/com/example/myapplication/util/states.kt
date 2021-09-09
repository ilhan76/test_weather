package com.example.myapplication.util

sealed class FeatureState {
    object Loading : FeatureState()
    object Default : FeatureState()
    class Success<T>(val content: T) : FeatureState()
    class Error(val error: String?) : FeatureState()
}