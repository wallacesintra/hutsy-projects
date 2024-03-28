package com.example.weather.network

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class OpenWeatherData (
    val cod: String,
    val cnt: Int,
//    @Contextual val list: List<WeatherList>,
    @Contextual val city: City,
){
    @Serializable
    data class City(
        val id: Int,
        val name: String,
        val country: String,
    )
//    @Serializable
//    data class WeatherList(
//        val dt: Int,
//        @Contextual val  main: Main,
//        @Contextual val weather: List<Weather>,
//        @Contextual val
//    )
}

