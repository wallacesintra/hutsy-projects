package com.example.weather.presentation.screens

import androidx.annotation.DrawableRes
import com.example.weather.R

sealed class Screen(val route: String, val label: String, @DrawableRes val icon: Int) {
    object Current: Screen("Current", "Home", R.drawable.cloudy)
    object Forecast: Screen("Forecast", "Forecast" , R.drawable.list)
}

val items = listOf(
    Screen.Current,
    Screen.Forecast
)