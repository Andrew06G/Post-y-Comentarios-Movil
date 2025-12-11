package com.example.postscommentsapp.di

import android.content.Context
import androidx.room.Room
import com.example.postscommentsapp.data.local.dao.CommentDao
import com.example.postscommentsapp.data.local.dao.PostDao
import com.example.postscommentsapp.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //Instruction to create objects
@InstallIn(SingletonComponent::class) //Important, only one db to all app.
object DatabaseModule {

    @Provides //It's a warning to say to the phone for create a database
    @Singleton //Only one db on all app
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "posts_comments_db"
        ).fallbackToDestructiveMigration() //If db changes tha old one is destroyed
            .build()

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()

    @Provides
    @Singleton
    fun provideCommentDao(db: AppDatabase): CommentDao = db.commentDao()
}
