package com.example.networkfetch.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photos(
    val id: String,
    @SerialName("img_src")
    val imgSrc: String
)