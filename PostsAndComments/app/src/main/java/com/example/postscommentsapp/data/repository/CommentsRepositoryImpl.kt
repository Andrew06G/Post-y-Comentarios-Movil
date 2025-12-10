package com.example.postscommentsapp.data.repository

import com.example.postscommentsapp.data.local.dao.CommentDao
import com.example.postscommentsapp.data.local.entity.CommentEntity
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val commentDao: CommentDao
) {

    override suspend fun getCommentsByPost(postId: Int): List<Comment> {
        return CommentMapper.toDomainList(
            commentDao.getCommentsByPost(postId)
        )
    }

    override suspend fun insertComment(comment: Comment) {
        val entity = CommentEntity(
            postId = comment.postId,
            comment = comment.comment,
            createdAt = comment.createdAt
        )
        commentDao.insertComment(entity)
    }
}
