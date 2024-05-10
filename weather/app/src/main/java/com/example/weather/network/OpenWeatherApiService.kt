package com.example.weather.network

import com.example.weather.BuildConfig
import com.example.weather.network.dto.CityWeatherData
import com.example.weather.network.dto.CurrentWeatherData
import retrofit2.http.GET
import retrofit2.http.Query


private const val api_key = BuildConfig.API_KEY


interface OpenWeatherApiService {
    @GET("forecast")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = api_key,
        @Query("units") units: String = "metric"
    ): CurrentWeatherData


    @GET("forecast")
    suspend fun getCityWeather(
        @Query("q") location: String,
        @Query("appid") appid: String = api_key,
        @Query("units") units: String = "metric"
    ) : CityWeatherData
}
