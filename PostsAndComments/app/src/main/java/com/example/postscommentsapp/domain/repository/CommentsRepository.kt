package com.example.postscommentsapp.domain.repository

import com.example.postscommentsapp.domain.model.Comment

interface CommentsRepository {
    suspend fun getCommentsByPost(postId: Int): List<Comment>
    suspend fun insertComment(comment: Comment)
}
