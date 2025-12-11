package com.example.postscommentsapp.di

import com.example.postscommentsapp.domain.repository.CommentsRepository
import com.example.postscommentsapp.domain.repository.PostsRepository
import com.example.postscommentsapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPostsUseCase(repo: PostsRepository) =
        GetPostsUseCase(repo)

    @Provides
    fun provideSearchPostsUseCase(repo: PostsRepository) =
        SearchPostsUseCase(repo)

    @Provides
    fun provideGetPostByIdUseCase(repo: PostsRepository) =
        GetPostByIdUseCase(repo)

    @Provides
    fun provideGetCommentsUseCase(repo: CommentsRepository) =
        GetCommentsUseCase(repo)

    @Provides
    fun provideInsertCommentUseCase(repo: CommentsRepository) =
        InsertCommentUseCase(repo)
}
