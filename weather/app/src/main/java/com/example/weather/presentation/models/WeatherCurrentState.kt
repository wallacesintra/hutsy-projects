package com.example.weather.presentation.models

sealed interface WeatherCurrentState {
    data class Success(val weatherUiState: CurrentWeatherUiState): WeatherCurrentState

    object Error: WeatherCurrentState

    object Loading: WeatherCurrentState

    object NetworkError: WeatherCurrentState
}