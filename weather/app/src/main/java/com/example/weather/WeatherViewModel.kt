package com.example.weather

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.weather.data.ReadJSON
import com.example.weather.data.WeatherData
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date

class WeatherViewModel(
    private val context: Context
): ViewModel(){
    private val  _uiState = MutableStateFlow(CurrentWeatherUiState())
    val uiState: StateFlow<CurrentWeatherUiState> = _uiState.asStateFlow()

    private fun loadData(){
        val jsonString = ReadJSON(context, "data.json")
        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json,_,_ ->
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                return@JsonDeserializer sdf.parse(json.asString)
            })
            .create()

        val data = gson.fromJson(jsonString, WeatherData::class.java)

        _uiState.update { currentState ->
            currentState.copy(
                place = data.city.name,
                country = data.city.country,
                temp = data.list[0].main.temp.toString(),
                message = data.list[0].weather[0].description,
                windSpeed = data.list[0].wind.speed.toString(),
                humidity = data.list[0].main.humidity.toString(),
                hourlyForecast = data.list
            )
        }
    }

    init {
        loadData()
    }
}