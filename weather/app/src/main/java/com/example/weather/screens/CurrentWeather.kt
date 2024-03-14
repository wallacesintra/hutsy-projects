package com.example.weather.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.CurrentWeatherUiState
import com.example.weather.components.CityDetails
import com.example.weather.components.HourlyForecast
import com.example.weather.components.WeatherDetails

@Composable
fun CurrentScreen(
    state: CurrentWeatherUiState
){
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        CityDetails(state.place, state.temp, state.message)
        WeatherDetails(speed = state.windSpeed, humidity = state.humidity, time = "8hr")
        state.hourlyForecast?.let { HourlyForecast(it) }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentScreenPreview(){
//    CurrentScreen()
}
