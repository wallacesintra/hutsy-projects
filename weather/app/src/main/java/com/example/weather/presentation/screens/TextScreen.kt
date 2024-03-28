package com.example.weather.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.network.OpenWeatherData
import com.example.weather.presentation.models.WeatherState
import com.example.weather.presentation.viewmodel.OpenWeatherViewModel

@Composable
fun TestScreen(
    weather: OpenWeatherData
){
    Text(text = weather.cnt.toString())
}

@Composable
fun TestContainer(){
    val weatherViewModel: OpenWeatherViewModel = viewModel(factory = OpenWeatherViewModel.Factory)

    val uiState = weatherViewModel.weatherState

    when (uiState) {
        is WeatherState.Loading -> LoadingScreen()
        is WeatherState.Error -> ErrorScreen()
        is WeatherState.Success -> TestScreen(weather = uiState.weather)
    }
}


@Composable
fun ErrorScreen(){
    Text(text = "Error It didn't Connect")
}

@Composable
fun LoadingScreen(){
    Text(text = "Loading.....")
}