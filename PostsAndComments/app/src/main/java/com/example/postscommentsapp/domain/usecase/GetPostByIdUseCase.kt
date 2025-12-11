package com.example.postscommentsapp.domain.usecase

import com.example.postscommentsapp.domain.model.Post
import com.example.postscommentsapp.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostByIdUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    suspend fun execute(postId: Int): Post? = repository.getPostById(postId)
}

