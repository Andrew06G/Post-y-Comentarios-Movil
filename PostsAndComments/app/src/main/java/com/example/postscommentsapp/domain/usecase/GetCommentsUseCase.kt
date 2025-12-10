package com.example.postscommentsapp.domain.usecase

import com.example.postscommentsapp.domain.model.Comment
import com.example.postscommentsapp.domain.repository.CommentsRepository
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val repository: CommentsRepository
) {
    suspend fun execute(postId: Int): List<Comment> = repository.getCommentsByPost(postId)
}
