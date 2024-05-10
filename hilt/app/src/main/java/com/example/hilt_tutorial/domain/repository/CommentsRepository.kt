package com.example.hilt_tutorial.domain.repository

import com.example.hilt_tutorial.network.dto.CommentDto

interface CommentsRepository {

    suspend fun getCommentByPostId(postId: Int): List<CommentDto>
}