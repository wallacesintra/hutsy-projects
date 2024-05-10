package com.example.hilt_tutorial.network.api

import com.example.hilt_tutorial.network.dto.CommentDto
import com.example.hilt_tutorial.network.dto.Post
import com.example.hilt_tutorial.network.dto.PostDto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
//import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceholderApi {
    @GET("posts")
    suspend fun getPosts(): List<PostDto>

    @GET("comments")
    suspend fun getPostComments(
        @Query("postId") postId: Int
    ): List<CommentDto>

//    companion object {
//        private const val BASE_URL ="https://jsonplaceholder.typicode.com/"
//        private const val BASE_URL ="https://jsonplace/"

//        private val json = Json{ ignoreUnknownKeys = true}
//        private val json = Json{{}}


//        fun create(): PlaceholderApi {
//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
////                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
//                .addConverterFactory(MoshiConverterFactory.create())
//                .build()
//                .create(PlaceholderApi::class.java)
//
//        }
//    }

}