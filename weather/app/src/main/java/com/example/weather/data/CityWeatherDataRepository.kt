package com.example.weather.data

import com.example.weather.network.OpenWeatherApiService
import com.example.weather.network.dto.CityWeatherData

interface CityWeatherDataRepository {
    suspend fun getCityWeatherData(): CityWeatherData
}


class NetworkCityWeatherDataRepository(
    private val openWeatherApiService: OpenWeatherApiService
): CityWeatherDataRepository{
    override suspend fun getCityWeatherData(): CityWeatherData = openWeatherApiService.getCityWeather()
}