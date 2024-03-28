package com.example.weather.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.weather.R

sealed class Screen(val route: String, val label: String, @DrawableRes val icon: Int?) {
    object Current: Screen("Current", "Home", R.drawable.cloudy)
    object Forecast: Screen("Forecast", "Forecast" , R.drawable.list)

    object Loading: Screen("Loading", "loading screen", null)

    object Error: Screen("Error", "Error Screen", null)
}

val items = listOf(
    Screen.Current,
    Screen.Forecast
)