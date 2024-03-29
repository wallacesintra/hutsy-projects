package com.example.weather.data

import com.example.weather.network.OpenWeatherApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val weatherDataRepository: WeatherDataRepository
    val cityWeatherDataRepository: CityWeatherDataRepository
}

class DefaultContainer: AppContainer {
    private val baseUrl = "https://api.openweathermap.org/data/2.5/"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: OpenWeatherApiService by lazy {
        retrofit.create(OpenWeatherApiService::class.java)
    }

    override val weatherDataRepository: WeatherDataRepository by lazy {
        NetworkWeatherDataRepository(retrofitService)
    }

    override val cityWeatherDataRepository: CityWeatherDataRepository by lazy {
        NetworkCityWeatherDataRepository(retrofitService)
    }
}