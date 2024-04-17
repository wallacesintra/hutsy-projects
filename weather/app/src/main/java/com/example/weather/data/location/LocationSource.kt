package com.example.weather.data.location

import com.example.weather.data.location.models.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationSource {
    suspend fun fetchCurrentLocation() : Flow<LocationModel>
}