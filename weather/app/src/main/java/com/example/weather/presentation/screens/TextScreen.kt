package com.example.weather.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.presentation.models.CityState
import com.example.weather.presentation.models.CityUiState
import com.example.weather.presentation.viewmodels.LocationsViewModel

@Composable
fun TestScreen(
    uiState: CityUiState
){
//    Text(text = uiState.country)
}

@Composable
fun TestContainer(){
//    val weatherViewModel: OpenWeatherViewModel = viewModel(factory = OpenWeatherViewModel.Factory)
    val locationsViewModel: LocationsViewModel = viewModel(factory = LocationsViewModel.Factory)

//    val uiState = weatherViewModel.weatherState

    val cityUiState = locationsViewModel.cityState
//    val cityUiState = weatherViewModel.cityState

//    when (cityUiState) {
//        is CityState.Loading -> TestLoadingScreen()
//        is CityState.Error -> TestErrorScreen()
//        is CityState.Success -> TestScreen(uiState = cityUiState.cityUiState)
////        CityState.Unsuccess -> {
////            Text(text = "Unsuccess")}
//    }

}


@Composable
fun TestErrorScreen(){
    Text(text = "Error It didn't Connect")
}

@Composable
fun TestLoadingScreen(){
    Text(text = "Loading.....")
}