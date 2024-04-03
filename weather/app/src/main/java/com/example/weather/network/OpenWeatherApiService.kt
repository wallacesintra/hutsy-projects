package com.example.weather.network

import com.example.weather.BuildConfig
import com.example.weather.network.dto.CityWeatherData
import com.example.weather.network.dto.CurrentWeatherData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val lat = -1.286389
private const val lon = 36.817223

private const val api = BuildConfig.API_KEY

private const val city = "alaska"

interface OpenWeatherApiService {
    @GET("forecast?lat=$lat&lon=$lon&appid=$api&units=metric")
    suspend fun getWeather(): CurrentWeatherData

    //get city data
//    @GET("forecast?q=$city&appid=$api&units=metric")
//    @GET("forecast?q={location}&appid=$api&units=metric")
//    suspend fun getCityWeather(@Query("location") location: String): CityWeatherData

    @GET("forecast")
    suspend fun getCityWeather(
        @Query("q") location: String,
        @Query("appid") appid: String = api,
        @Query("units") units: String = "metric"
    ) : CityWeatherData
}
