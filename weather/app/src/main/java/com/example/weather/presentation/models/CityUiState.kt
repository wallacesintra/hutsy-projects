package com.example.weather.presentation.models

import com.example.weather.data.local.LocationEntity
import com.example.weather.network.dto.WeatherList

data class CityUiState(
    val locationList: List<LocationEntity> = emptyList(),
//    val name: String = "",
//    val country: String = "",
//    val temp: String = "",
//    val mainWeather: String = ""
)