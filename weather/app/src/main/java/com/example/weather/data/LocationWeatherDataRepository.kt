package com.example.weather.data

import com.example.weather.network.OpenWeatherApiService
import com.example.weather.network.dto.CityWeatherData

interface LocationWeatherDataRepository {
    suspend fun getCityWeatherData(location: String): CityWeatherData
}


class NetworkLocationWeatherDataRepository(
    private val openWeatherApiService: OpenWeatherApiService
): LocationWeatherDataRepository{
    override suspend fun getCityWeatherData(location: String): CityWeatherData = openWeatherApiService.getCityWeather(location)
}