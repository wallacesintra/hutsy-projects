package com.example.weather.presentation.viewmodels

import android.app.Activity
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
import com.example.weather.data.location.LocationDataSource
import com.example.weather.data.location.models.LocationModel
import com.example.weather.presentation.models.CurrentWeatherUiState
import com.example.weather.presentation.models.WeatherCurrentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class CurrentLocationWeatherViewModel(
    private val weatherDataRepository: WeatherDataRepository,
    private val locationDataSource: LocationDataSource,
) : ViewModel() {
    var weatherCurrentState: WeatherCurrentState by mutableStateOf(WeatherCurrentState.Loading)
        private set

    private val _location = MutableStateFlow<LocationModel?>(null)
    val location: StateFlow<LocationModel?> get() = _location

    private val latitude = location.value?.latitude
    private val longitude = location.value?.longitude

    init {
//        fetchLocation()
        getWeatherData()
    }

    private suspend fun fetchLocation(){
        viewModelScope.launch {
            _location.value = locationDataSource.fetchCurrentLocation().first()
        }
    }

    private fun getWeatherData(){
        viewModelScope.launch {
            fetchLocation()

            try {
                weatherCurrentState = WeatherCurrentState.Loading



                val lat = latitude ?: 1.0
                val lon = longitude ?: 1.0

                if (lat == 0.0&& lon == 0.0){
                    weatherCurrentState = WeatherCurrentState.NetworkError
                }

                val weatherData = weatherDataRepository.getWeatherData(lat = lat, lon = lon)
                weatherCurrentState = try {
                    WeatherCurrentState.Success(
                        weatherUiState =CurrentWeatherUiState(
                            place = weatherData.city.name,
                            country = weatherData.city.country,
                            temp = weatherData.list[0].main.temp.toInt().toString(),
                            windSpeed = weatherData.list[0].wind.speed.toInt().toString(),
                            humidity = weatherData.list[0].main.humidity.toString(),
                            hourlyForecast = weatherData.list
                        )
                    )
                } catch (e: Exception) {
                    WeatherCurrentState.NetworkError
                }

            } catch (e: Exception){
                weatherCurrentState = WeatherCurrentState.NetworkError
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WeatherApplication)
                val weatherDataRepository = application.container.weatherDataRepository
                val locationDataSource = application.container.locationDataSource
                CurrentLocationWeatherViewModel(weatherDataRepository, locationDataSource)
            }
        }
    }
}