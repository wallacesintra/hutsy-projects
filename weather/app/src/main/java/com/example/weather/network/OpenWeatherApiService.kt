package com.example.weather.network

import com.example.weather.BuildConfig
import retrofit2.http.GET

private const val lat = -1.286389
private const val lon = 36.817223

private const val api = BuildConfig.API_KEY

interface OpenWeatherApiService {
    @GET("forecast?lat=$lat&lon=$lon&appid=$api&units=metric")
    suspend fun getWeather(): OpenWeatherData
}
