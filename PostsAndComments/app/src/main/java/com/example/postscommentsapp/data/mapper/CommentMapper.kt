package com.example.postscommentsapp.data.mapper

import com.example.postscommentsapp.data.local.entity.CommentEntity
import com.example.postscommentsapp.domain.model.Comment

object CommentMapper {

    fun toDomain(entity: CommentEntity): Comment =
        Comment(
            id = entity.id,
            postId = entity.postId,
            comment = entity.comment,
            createdAt = entity.createdAt
        )

    fun toEntity(domain: Comment): CommentEntity =
        CommentEntity(
            domain.id,
            domain.postId,
            domain.comment,
            domain.createdAt
        )

    fun toDomainList(list: List<CommentEntity>): List<Comment> =
        list.map { toDomain(it) }
}
