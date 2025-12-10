package com.example.postscommentsapp.data.remote

import com.example.postscommentsapp.data.remote.dto.PostDto
import retrofit2.http.GET

interface JSONPlaceholderService {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}
