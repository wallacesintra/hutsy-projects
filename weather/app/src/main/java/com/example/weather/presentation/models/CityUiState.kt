package com.example.weather.presentation.models

import com.example.weather.network.dto.WeatherList

data class CityUiState(
    val name: String = "",
    val country: String = "",
    val temp: String = "",
    val list: List<WeatherList>
)