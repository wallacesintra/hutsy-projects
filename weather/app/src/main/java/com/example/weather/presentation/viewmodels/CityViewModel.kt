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
import com.example.weather.data.local.LocationDao
import com.example.weather.data.local.LocationEntity
import com.example.weather.presentation.models.CityState
import com.example.weather.presentation.models.CityUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CityViewModel(
    private val cityWeatherDataRepository: CityWeatherDataRepository,
    private val dao: LocationDao

): ViewModel() {
    var cityState: CityState by mutableStateOf(CityState.Loading)
        private set

    private val _state = MutableStateFlow(CityUiState())
    private val _locations = _state
        .flatMapLatest {value ->
            dao.displayAll()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val state = combine(_state, _locations) { state, locations ->
        state.copy(
            locationList = locations
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), CityUiState())

    var userLocation: String by mutableStateOf("")
        private set

    var userLocationsList: List<LocationEntity> by mutableStateOf(listOf())
        private set

    fun onLocationChange(location: String){
        userLocation = location
    }

    fun searchLocation(){
        getLocationData(userLocation)
        userLocation = ""
    }


    private fun getLocationData(location: String) {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                cityState = CityState.Loading
                val cityData = cityWeatherDataRepository.getCityWeatherData(location)

                cityState = CityState.Success(
                    cityUiState = CityUiState(
//                        name = cityData.city.name,
//                        country = cityData.city.country,
//                        temp = cityData.list[0].main.temp.toInt().toString(),
//                        mainWeather = cityData.list[0].weather[0].main
                    )
                )

                val searchedLocation = LocationEntity(
                    name = cityData.city.name,
                    country = cityData.city.country,
                    temp = cityData.list[0].main.temp.toInt().toString(),
                    mainWeather = cityData.list[0].weather[0].main
                )
                userLocationsList = userLocationsList + searchedLocation
                dao.insertLocation(searchedLocation)

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
                val locationDao = application.container.locationDB.dao
                CityViewModel(cityWeatherDataRepository, locationDao)
            }
        }
    }

}