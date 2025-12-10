package com.example.postscommentsapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.postscommentsapp.data.local.dao.CommentDao
import com.example.postscommentsapp.data.local.dao.PostDao
import com.example.postscommentsapp.data.local.entity.CommentEntity
import com.example.postscommentsapp.data.local.entity.PostEntity

@Database(
    entities = [PostEntity::class, CommentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao
}
