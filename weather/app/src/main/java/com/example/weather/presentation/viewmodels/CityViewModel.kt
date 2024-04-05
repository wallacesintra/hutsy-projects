package com.example.weather.presentation.viewmodels

import android.net.http.HttpException
import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class CityViewModel(
    private val cityWeatherDataRepository: CityWeatherDataRepository
): ViewModel() {
    var cityState: CityState by mutableStateOf(CityState.Loading)
        private set

    var userLocation: String by mutableStateOf("")
        private set

    var userLocationsList: List<CityUiState> by mutableStateOf(listOf())
        private set

    fun onLocationChange(location: String){
        userLocation = location
    }

    fun searchLocation(){
        getLocationData(userLocation)
        userLocation = ""
    }



    private fun getLocationData(location: String) {
        viewModelScope.launch {
            try {
                cityState = CityState.Loading
                val cityData = cityWeatherDataRepository.getCityWeatherData(location)

                cityState = CityState.Success(
                    cityUiState = CityUiState(
                        name = cityData.city.name,
                        country = cityData.city.country,
                        temp = cityData.list[0].main.temp.toInt().toString(),
                        mainWeather = cityData.list[0].weather[0].main
                    )
                )

                userLocationsList = userLocationsList + CityUiState(
                    name = cityData.city.name,
                    country = cityData.city.country,
                    temp = cityData.list[0].main.temp.toInt().toString(),
                    mainWeather = cityData.list[0].weather[0].main
                )

            } catch (e: Exception) {
                cityState = CityState.Error
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