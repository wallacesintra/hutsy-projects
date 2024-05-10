package com.example.hilt_tutorial.data.repository

import com.example.hilt_tutorial.domain.repository.CommentsRepository
import com.example.hilt_tutorial.network.api.PlaceholderApi
import com.example.hilt_tutorial.network.dto.CommentDto
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val placeholderApi: PlaceholderApi
): CommentsRepository {

    override suspend fun getCommentByPostId(postId: Int): List<CommentDto> = placeholderApi.getPostComments(postId)

}