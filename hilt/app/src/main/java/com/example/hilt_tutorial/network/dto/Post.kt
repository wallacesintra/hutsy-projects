package com.example.hilt_tutorial.network.dto

import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

//data class Post(
//    @field: SerialName("userid") val userId: Int,
//
//)

data class PostDto(

    @Json(name = "body")
    val body: String,

    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "userId")
    val userId: Int
)