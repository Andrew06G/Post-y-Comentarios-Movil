package com.example.postscommentsapp.data.repository

import com.example.postscommentsapp.data.local.dao.PostDao
import com.example.postscommentsapp.data.remote.RemoteDataSource
import com.example.postscommentsapp.data.mapper.PostMapper
import com.example.postscommentsapp.domain.model.Post
import com.example.postscommentsapp.domain.repository.PostsRepository
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postDao: PostDao,
    private val remote: RemoteDataSource
) : PostsRepository {

    override suspend fun getPosts(): List<Post> {
        val local = postDao.getAllPosts()
        if (local.isNotEmpty()) {
            return PostMapper.toDomainList(local)
        }
        val remoteDtos = remote.fetchPosts()
        val remotePosts = PostMapper.fromDtoList(remoteDtos)
        val entities = PostMapper.toEntityList(remotePosts)
        postDao.insertPosts(entities)
        return remotePosts
    }

    override suspend fun searchPosts(query: String): List<Post> {
        val local = postDao.getAllPosts()
        return PostMapper.toDomainList(
            local.filter { it.title.contains(query, ignoreCase = true) }
        )
    }
}
