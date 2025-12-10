package com.example.postscommentsapp.di

import com.example.postscommentsapp.domain.repository.CommentsRepository
import com.example.postscommentsapp.domain.repository.PostsRepository
import com.example.postscommentsapp.data.repository.CommentsRepositoryImpl
import com.example.postscommentsapp.data.repository.PostsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPostsRepository(
        impl: PostsRepositoryImpl
    ): PostsRepository

    @Binds
    @Singleton
    abstract fun bindCommentsRepository(
        impl: CommentsRepositoryImpl
    ): CommentsRepository
}
