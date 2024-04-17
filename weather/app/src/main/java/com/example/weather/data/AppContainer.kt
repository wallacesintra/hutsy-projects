package com.example.weather.data

import android.content.Context
import androidx.room.Room
import com.example.weather.data.local.LocationDatabase
import com.example.weather.data.location.LocationDataSource
import com.example.weather.network.OpenWeatherApiService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val weatherDataRepository: WeatherDataRepository
    val locationWeatherDataRepository: LocationWeatherDataRepository
    val locationDB: LocationDatabase
    val locationDataSource: LocationDataSource
}

class DefaultContainer(private val applicationContext: Context): AppContainer {
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

    override val locationWeatherDataRepository: LocationWeatherDataRepository by lazy {
        NetworkLocationWeatherDataRepository(retrofitService)
    }

    override val locationDB by lazy {
        Room.databaseBuilder(
            applicationContext,
            LocationDatabase::class.java,
            "locations.db"
        ).build()
    }

    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(applicationContext)

    override val locationDataSource: LocationDataSource by lazy {
        LocationDataSource(
            applicationContext,
            fusedLocationClient,
            LocationRequest.create()
        )
    }
}