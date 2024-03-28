package com.example.weather.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.presentation.models.CurrentWeatherUiState
import com.example.weather.presentation.models.WeatherState
import com.example.weather.presentation.viewmodels.OpenWeatherViewModel

@Composable
fun TestScreen(
    uiState: CurrentWeatherUiState
){
    Text(text = uiState.country)
}

@Composable
fun TestContainer(){
    val weatherViewModel: OpenWeatherViewModel = viewModel(factory = OpenWeatherViewModel.Factory)

    val uiState = weatherViewModel.weatherState

    when (uiState) {
        is WeatherState.Loading -> TestLoadingScreen()
        is WeatherState.Error -> TestErrorScreen()
        is WeatherState.Success -> TestScreen(uiState = uiState.weatherUiState)
    }
}


@Composable
fun TestErrorScreen(){
    Text(text = "Error It didn't Connect")
}

@Composable
fun TestLoadingScreen(){
    Text(text = "Loading.....")
}