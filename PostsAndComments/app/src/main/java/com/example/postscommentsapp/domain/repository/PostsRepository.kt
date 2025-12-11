package com.example.postscommentsapp.domain.repository

import com.example.postscommentsapp.domain.model.Post

interface PostsRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getPostById(postId: Int): Post?
    suspend fun searchPosts(query: String): List<Post>
}
