package com.example.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.weather.R

@Composable
fun HourlyForecast(){
    Column {
        RowIconText(icon = R.drawable.schedule, text = "Hourly Forecast", 36, 28)
    }
}