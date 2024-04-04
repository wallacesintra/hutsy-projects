package com.example.weather.data

import com.example.weather.network.OpenWeatherApiService
import com.example.weather.network.dto.CurrentWeatherData

interface WeatherDataRepository {
    suspend fun getWeatherData(lat: Double, lon: Double): CurrentWeatherData
}

class NetworkWeatherDataRepository(
    private val openWeatherApiService: OpenWeatherApiService
): WeatherDataRepository{
    override suspend fun getWeatherData(lat: Double, lon: Double): CurrentWeatherData = openWeatherApiService.getWeather(lat, lon)
}