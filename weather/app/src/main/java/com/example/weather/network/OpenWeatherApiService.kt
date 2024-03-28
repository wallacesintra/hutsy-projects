package com.example.weather.network

import retrofit2.http.GET

private const val lat = -1.286389
private const val lon = 36.817223
private const val fungo = "13b953f5b97dab992cd3fb450d23cf26"

interface OpenWeatherApiService {
    @GET("forecast?lat=$lat&lon=$lon&appid=$fungo&units=metric")
    suspend fun getWeather(): OpenWeatherData
}
