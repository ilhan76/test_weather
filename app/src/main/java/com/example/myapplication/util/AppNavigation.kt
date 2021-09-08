package com.example.myapplication.util

import android.os.Bundle

interface AppNavigation {
    fun toDetail(bundle: Bundle)
    fun toHome()
}