package com.example.weather.presentation.models


import com.example.weather.data.demo.WeatherData
import com.example.weather.network.OpenWeatherData

data class CurrentWeatherUiState (
    val place: String = "",
    val country: String = "",
    val temp: String = "",
    val message: String = "",
    val windSpeed: String = "",
    val humidity: String = "",
    val hourlyForecast: List<OpenWeatherData.WeatherList> = listOf()
)
