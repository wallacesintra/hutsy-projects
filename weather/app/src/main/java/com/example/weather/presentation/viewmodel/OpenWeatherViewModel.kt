package com.example.weather.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weather.WeatherApplication
import com.example.weather.data.WeatherDataRepository
import com.example.weather.presentation.models.WeatherState
import kotlinx.coroutines.launch
import okio.IOException

class OpenWeatherViewModel(
    private val weatherDataRepository: WeatherDataRepository
) : ViewModel() {
    var weatherState: WeatherState by mutableStateOf(WeatherState.Loading)
        private set

    init {
        getWeatherData()
    }

    private fun getWeatherData(){
        viewModelScope.launch {
            weatherState = WeatherState.Loading
            weatherState = try {
                WeatherState.Success(weatherDataRepository.getWeatherData())
            } catch (e: IOException) {
                WeatherState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WeatherApplication)
                val weatherDataRepository = application.container.weatherDataRepository
                OpenWeatherViewModel(weatherDataRepository)
            }
        }
    }
}