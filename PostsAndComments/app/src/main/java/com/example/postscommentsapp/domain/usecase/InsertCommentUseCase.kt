package com.example.postscommentsapp.domain.usecase

import com.example.postscommentsapp.domain.model.Comment
import com.example.postscommentsapp.domain.repository.CommentsRepository
import javax.inject.Inject

class InsertCommentUseCase @Inject constructor(
    private val repository: CommentsRepository
) {
    suspend fun execute(comment: Comment) = repository.insertComment(comment)
}
