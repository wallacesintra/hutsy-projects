package com.example.weather.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weather.presentation.components.CityDetails
import com.example.weather.presentation.components.HourlyForecast
import com.example.weather.presentation.components.WeatherDetails
import com.example.weather.presentation.models.CurrentWeatherUiState

@Composable
fun CurrentScreen(
    state: CurrentWeatherUiState
){
    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CityDetails(
            iconName = state.hourlyForecast[0].weather[0].main,
            place = "${state.place}, ${state.country}",
            temp = state.temp,
            message = state.message
        )
        WeatherDetails(speed = state.windSpeed, humidity = state.humidity, cloudiness = state.hourlyForecast[0].clouds.all.toString())
        HourlyForecast(state.hourlyForecast)
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentScreenPreview(){
//    CurrentScreen()
}
