package com.example.weather.presentation.models

import com.example.weather.network.OpenWeatherData

sealed interface WeatherState {
    data class Success(val weather: OpenWeatherData): WeatherState

    object Error: WeatherState

    object Loading: WeatherState
}