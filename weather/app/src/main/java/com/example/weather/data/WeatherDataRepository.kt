package com.example.weather.data

import com.example.weather.network.OpenWeatherApiService
import com.example.weather.network.dto.CurrentWeatherData

interface WeatherDataRepository {
    suspend fun getWeatherData(): CurrentWeatherData
}

class NetworkWeatherDataRepository(
    private val openWeatherApiService: OpenWeatherApiService
): WeatherDataRepository{
    override suspend fun getWeatherData(): CurrentWeatherData = openWeatherApiService.getWeather()
}