package com.example.weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocationEntity(
    val name: String,
    val country: String,
    val temp: String,
    val mainWeather: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)