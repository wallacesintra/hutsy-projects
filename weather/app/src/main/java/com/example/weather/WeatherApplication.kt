package com.example.weather

import android.app.Application
import com.example.weather.data.AppContainer
import com.example.weather.data.DefaultContainer

class WeatherApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer(this)
    }
}