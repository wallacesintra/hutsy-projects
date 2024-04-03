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
import com.example.weather.presentation.events.CityActions
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
//        getCityWeatherData("alaska")
    }

    fun onAction(action: CityActions) {
        when(action){
            is CityActions.Search -> getCityWeatherData(action.location)
        }
    }

    private fun getCityWeatherData( location: String){
        viewModelScope.launch {
            cityState = CityState.Loading
            val cityData = cityWeatherDataRepository.getCityWeatherData(location)

            if (cityData.cod == "200"){
                cityState = try {
                    CityState.Success(
                        cityUiState = CityUiState(
                            name = cityData.city.name,
                            country = cityData.city.country,
                            temp = cityData.list[0].main.temp.toInt().toString(),
                            found = true,
                            list = cityData.list
                        )
                    )
                } catch (e: IOException){
                    CityState.Error
                }
            }
            else {
                cityState = CityState.Success(
                    cityUiState = CityUiState(
                        found = false
                    )
                )
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