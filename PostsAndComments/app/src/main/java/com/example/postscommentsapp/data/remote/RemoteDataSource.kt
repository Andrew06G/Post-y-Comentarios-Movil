package com.example.postscommentsapp.data.remote

import com.example.postscommentsapp.data.remote.dto.PostDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: JSONPlaceholderService
) {
    suspend fun fetchPosts(): List<PostDto> = api.getPosts()
}
