package com.example.postscommentsapp.domain.model

data class Comment(
    val id: Int,
    val postId: Int,
    val comment: String,
    val createdAt: Long
)
