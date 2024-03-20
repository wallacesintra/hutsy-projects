package com.example.weather.screens

import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.weather.R

sealed class Screen(val route: String, val label: String, @DrawableRes val icon: Int) {
    object Current: Screen("Current", "Home", R.drawable.cloudy)
    object Forecast: Screen("Forecast", "Forecast" , R.drawable.list)
}

val items = listOf(
    Screen.Current,
    Screen.Forecast
)