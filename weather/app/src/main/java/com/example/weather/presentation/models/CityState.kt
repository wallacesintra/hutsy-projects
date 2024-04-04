package com.example.weather.presentation.models

sealed interface CityState {
    data class Success(val cityUiState: CityUiState): CityState

    object Unsuccess: CityState

    object Error: CityState

    object Loading: CityState
}