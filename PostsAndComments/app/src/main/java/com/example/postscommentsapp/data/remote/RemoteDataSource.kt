package com.example.postscommentsapp.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: JSONPlaceholderService
) {

    suspend fun fetchPosts(): List<PostDto> {
        return api.getPosts()
    }
}
