package com.example.weather.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.weather.presentation.components.CityComponent
import com.example.weather.presentation.models.CityUiState
import com.example.weather.presentation.viewmodels.CityViewModel

@Composable
fun CityScreen(
    state: CityUiState
){
    val viewModel: CityViewModel
    Column {
        Text(text = "Your locations")
        CityComponent(
            iconName =  state.list[0].weather.main,
            temp = state.temp,
            name = "${state.name}, ${state.country}"
        )

    }
}