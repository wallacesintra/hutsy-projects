package com.example.weather.data

import com.example.weather.network.OpenWeatherApiService
import com.example.weather.network.OpenWeatherData

interface WeatherDataRepository {
    suspend fun getWeatherData(): OpenWeatherData
}

class NetworkWeatherDataRepository(
    private val openWeatherApiService: OpenWeatherApiService
): WeatherDataRepository{
    override suspend fun getWeatherData(): OpenWeatherData = openWeatherApiService.getWeather()
}