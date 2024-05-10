package com.example.hilt_tutorial.presentation.models

import com.example.hilt_tutorial.network.dto.Post
import com.example.hilt_tutorial.network.dto.PostDto

sealed interface PostState {

    data class Success(val postList: List<PostDto>): PostState

    data class Error(val message: String): PostState

    data object Loading: PostState

}