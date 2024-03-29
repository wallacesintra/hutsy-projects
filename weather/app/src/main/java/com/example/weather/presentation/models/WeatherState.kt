package com.example.weather.presentation.models

sealed interface WeatherState {
    data class Success(val weatherUiState: CurrentWeatherUiState): WeatherState

    object Error: WeatherState

    object Loading: WeatherState
}