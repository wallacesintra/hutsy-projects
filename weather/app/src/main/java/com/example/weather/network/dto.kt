package com.example.weather.network

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.DateFormat

@Serializable
data class OpenWeatherData (
    val cod: String,
    val cnt: Int,
    @Contextual val list: List<WeatherList>,
    @Contextual val city: City,
){
    @Serializable
    data class City(
        val id: Int,
        val name: String,
        val country: String,
    )
    @Serializable
    data class WeatherList(
        val dt: Int,
        @Contextual val  main: Main,
        @Contextual val weather: List<Weather>,
        @Contextual val clouds: Clouds,
        @Contextual val wind: Wind,
        @SerialName("dt_txt")
        val dtTxt: String
    ) {

        @Serializable
        data class Main(
            val temp: Double,
            @SerialName("feels_like")
            val feelsLike: Double,

            @SerialName("temp_min")
            val minTemp: Double,

            @SerialName("temp_max")
            val maxTemp: Double,

            val pressure: Int,
            val humidity: Int,
        )
        @Serializable
        data class Weather(
            val id: Int,
            val main: String,
            val description: String,
            val icon: String
        )

        @Serializable
        data class Clouds(
            val all: Int
        )
        @Serializable
        data class Wind(
            val speed: Float,
            val deg: Float,
            val gust: Float
        )
    }
}

