package com.example.weather.network.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherData (
    val cod: String,
    val list: List<WeatherList>,
    @Contextual val city: City
)

@Serializable
data class City(
    val name: String,
    val country: String
)


@Serializable
data class WeatherList(
    @Contextual val main: Main,
    @Contextual val weather: List<Weather>
)


@Serializable
data class Main(
    val temp: Double
)

@Serializable
data class Weather(
    val main: String
)
