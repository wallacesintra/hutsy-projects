package com.example.hilt_tutorial.data.repository

import com.example.hilt_tutorial.domain.repository.PostsRepository
import com.example.hilt_tutorial.network.api.PlaceholderApi
import com.example.hilt_tutorial.network.dto.Post
import com.example.hilt_tutorial.network.dto.PostDto
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val placeholderApi: PlaceholderApi
): PostsRepository {
    override suspend fun getPlaceholderPosts() : List<PostDto> = placeholderApi.getPosts()
}