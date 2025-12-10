package com.example.postscommentsapp.data.mapper

import com.example.postscommentsapp.data.local.entity.PostEntity
import com.example.postscommentsapp.data.remote.dto.PostDto
import com.example.postscommentsapp.domain.model.Post

object PostMapper {

    fun fromDto(dto: PostDto): Post {
        return Post(
            id = dto.id,
            title = dto.title,
            body = dto.body
        )
    }

    fun fromDtoList(list: List<PostDto>): List<Post> =
        list.map { fromDto(it) }

    fun toEntity(post: Post): PostEntity {
        return PostEntity(
            post.id,
            post.title,
            post.body
        )
    }

    fun toEntityList(list: List<Post>): List<PostEntity> =
        list.map { toEntity(it) }

    fun toDomain(entity: PostEntity): Post {
        return Post(
            id = entity.id,
            title = entity.title,
            body = entity.body
        )
    }

    fun toDomainList(list: List<PostEntity>): List<Post> =
        list.map { toDomain(it) }
}