package com.example.networkfetch.data

import com.example.networkfetch.network.MarsApiService
import com.example.networkfetch.network.MarsPhoto

interface MarsPhotoRepository {
    suspend fun getMarsPhoto(): List<MarsPhoto>
}

class NetworkMarsPhotoRepository(
    private val marsApiService: MarsApiService
): MarsPhotoRepository {
    override suspend fun getMarsPhoto(): List<MarsPhoto>  = marsApiService.getPhotos()
}