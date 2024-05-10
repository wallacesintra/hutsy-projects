package com.example.hilt_tutorial.domain.repository

import com.example.hilt_tutorial.network.dto.Post
import com.example.hilt_tutorial.network.dto.PostDto

interface PostsRepository {
    suspend fun getPlaceholderPosts(): List<PostDto>
}