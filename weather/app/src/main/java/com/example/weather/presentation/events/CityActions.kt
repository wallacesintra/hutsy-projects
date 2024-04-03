package com.example.weather.presentation.events

sealed class CityActions {
    data class Search(val location: String): CityActions()
}