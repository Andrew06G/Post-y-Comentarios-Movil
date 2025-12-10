package com.example.postscommentsapp.data.repository

import com.example.postscommentsapp.data.local.dao.CommentDao
import com.example.postscommentsapp.data.local.entity.CommentEntity
import com.example.postscommentsapp.data.mapper.CommentMapper
import com.example.postscommentsapp.domain.model.Comment
import com.example.postscommentsapp.domain.repository.CommentsRepository
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val commentDao: CommentDao
) : CommentsRepository {

    override suspend fun getCommentsByPost(postId: Int): List<Comment> {
        return CommentMapper.toDomainList(commentDao.getCommentsByPost(postId))
    }

    override suspend fun insertComment(comment: Comment) {
        val entity = CommentEntity(
            id = 0,
            postId = comment.postId,
            comment = comment.comment,
            createdAt = comment.createdAt
        )
        commentDao.insertComment(entity)
    }
}
