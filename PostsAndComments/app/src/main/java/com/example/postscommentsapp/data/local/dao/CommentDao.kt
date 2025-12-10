package com.example.postscommentsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.postscommentsapp.data.local.entity.CommentEntity

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments WHERE postId = :postId ORDER BY createdAt DESC")
    suspend fun getCommentsByPost(postId: Int): List<CommentEntity>

    @Insert
    suspend fun insertComment(comment: CommentEntity)
}
