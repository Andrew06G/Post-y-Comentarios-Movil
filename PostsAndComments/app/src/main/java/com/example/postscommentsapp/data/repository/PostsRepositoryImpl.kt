package com.example.postscommentsapp.data.repository

import com.example.postscommentsapp.data.local.dao.PostDao
import com.example.postscommentsapp.data.remote.RemoteDataSource
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postDao: PostDao,
    private val remote: RemoteDataSource
) {

    override suspend fun getPosts(): List<Post> {

        val local = postDao.getAllPosts()
        if (local.isNotEmpty()) {
            return PostMapper.toDomainList(local)
        }

        // 2. Obtener remoto
        val remoteDtos = remote.fetchPosts()

        // 3. Convertir DTO → dominio
        val remotePosts = PostMapper.fromDtoList(remoteDtos)

        // 4. Convertir dominio → entities
        val entities = PostMapper.toEntityList(remotePosts)

        // 5. Guardar
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
