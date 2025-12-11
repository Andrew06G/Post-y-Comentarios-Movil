package com.example.postscommentsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postscommentsapp.data.local.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    suspend fun getAllPosts(): List<PostEntity>

    @Query("SELECT * FROM posts WHERE id = :postId LIMIT 1")
    suspend fun getPostById(postId: Int): PostEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE) //On conflic is to update the date with the same id
    suspend fun insertPosts(posts: List<PostEntity>)
}
