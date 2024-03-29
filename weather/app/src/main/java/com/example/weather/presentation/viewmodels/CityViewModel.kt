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
import com.example.weather.data.CityWeatherDataRepository
import com.example.weather.presentation.models.CityState
import com.example.weather.presentation.models.CityUiState
import kotlinx.coroutines.launch
import java.io.IOException

class CityViewModel(
    private val cityWeatherDataRepository: CityWeatherDataRepository
): ViewModel() {
    var cityState: CityState by mutableStateOf(CityState.Loading)
        private set

    init {
        getCityWeatherData()
    }

    private fun getCityWeatherData(){
        viewModelScope.launch {
            cityState = CityState.Loading
            val cityData = cityWeatherDataRepository.getCityWeatherData()

            cityState = try {
                CityState.Success(
                    cityUiState = CityUiState(
                        name = cityData.city.name,
                        country = cityData.city.country,
                        temp = cityData.list[0].main.temp.toInt().toString(),
                        list = cityData.list
                    )
                )
            } catch (e: IOException){
                CityState.Error
            }
        }
    }
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WeatherApplication)
                val cityWeatherDataRepository = application.container.cityWeatherDataRepository
                CityViewModel(cityWeatherDataRepository)
            }
        }
    }

}