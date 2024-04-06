package com.example.weather.presentation.viewmodels

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
import com.example.weather.presentation.models.CurrentWeatherUiState
import com.example.weather.presentation.models.WeatherState
import kotlinx.coroutines.launch
import okio.IOException

class CurrentLocationWeatherViewModel(
    private val weatherDataRepository: WeatherDataRepository,
) : ViewModel() {
    var weatherState: WeatherState by mutableStateOf(WeatherState.Loading)
        private set

    private val latitude = -1.286389
    private val longitude = 36.817223

    init {
        getWeatherData()
    }

    private fun getWeatherData(){
        viewModelScope.launch {
            weatherState = WeatherState.Loading
            val weatherData = weatherDataRepository.getWeatherData(lat = latitude, lon = longitude)
            weatherState = try {
                WeatherState.Success(
                    weatherUiState =CurrentWeatherUiState(
                        place = weatherData.city.name,
                        country = weatherData.city.country,
                        temp = weatherData.list[0].main.temp.toInt().toString(),
                        windSpeed = weatherData.list[0].wind.speed.toInt().toString(),
                        humidity = weatherData.list[0].main.humidity.toString(),
                        hourlyForecast = weatherData.list
                    )
                )
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
                CurrentLocationWeatherViewModel(weatherDataRepository)
            }
        }
    }
}