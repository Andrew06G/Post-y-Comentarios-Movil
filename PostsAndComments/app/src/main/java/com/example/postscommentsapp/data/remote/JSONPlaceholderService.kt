package com.example.postscommentsapp.data.remote

import retrofit2.http.GET

interface JSONPlaceholderService {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}
