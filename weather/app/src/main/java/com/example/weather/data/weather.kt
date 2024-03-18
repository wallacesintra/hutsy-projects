package com.example.weather.data

import java.util.Date

data class WeatherData(
    val cod: String,
    val message: String,
    val cnt: Int,
    val list: List<WeatherDetails>,
    val city: City
){
    data class City (
        val id: Int,
        val name: String,
        val coord: Coord,
        val country: String,
        val population: Int,
        val timezone: Int,
        val sunrise: Int,
        val sunset: Int
    ){
        data class Coord(
            val lat: Float,
            val lon: Float
        )
    }
    data class WeatherDetails(
        val dt: Int,
        val main: Main,
        val weather: List<Weather>,
        val clouds: Clouds,
        val wind: Wind,
        val visibility: Int,
        val pop: Double,
        val sys: Sys,
        val dt_txt: Date
    ){
        data class Main(
            val temp: Double,
            val feels_like: Double,
            val temp_min: Double,
            val temp_max: Double,
            val pressure: Int,
            val sea_level: Int,
            val grnd_level: Int,
            val humidity: Int,
            val temp_kf: Float
        )

        data class Weather(
            val id: Int,
            val main: String,
            val description: String,
            val icon: String
        )
        data class Clouds(
            val all: Int
        )
        data class Wind(
            val speed: Float,
            val deg: Float,
            val gust: Float
        )
        data class Sys (
            val pod: String
        )
    }
}
