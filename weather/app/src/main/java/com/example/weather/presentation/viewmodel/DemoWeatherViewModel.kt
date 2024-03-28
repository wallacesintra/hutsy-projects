package com.example.weather.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.demo.ReadJSON
import com.example.weather.data.demo.WeatherData
import com.example.weather.presentation.models.CurrentWeatherUiState
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class WeatherViewModel(
    private val context: Context
): ViewModel(){
    private val  _uiState = MutableStateFlow(CurrentWeatherUiState())
    val uiState: StateFlow<CurrentWeatherUiState> = _uiState.asStateFlow()


    private fun loadData(){
        val jsonString = ReadJSON(context, "data.json")
        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json,_,_ ->
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//                val sdf = SimpleDateFormat("hh")
                return@JsonDeserializer sdf.parse(json.asString)
            })
            .create()

        val data = gson.fromJson(jsonString, WeatherData::class.java)

        _uiState.update { currentState ->
            currentState.copy(
                place = data.city.name,
                country = data.city.country,
                temp = data.list[0].main.temp.toInt().toString(),
                message = data.list[0].weather[0].description,
                windSpeed = data.list[0].wind.speed.toInt().toString(),
                humidity = data.list[0].main.humidity.toString(),
                hourlyForecast = data.list
            )
        }
    }

    init {
        loadData()
    }
}