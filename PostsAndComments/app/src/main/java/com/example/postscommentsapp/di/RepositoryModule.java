package com.example.postscommentsapp.di;

import com.example.postscommentsapp.data.repository.CommentsRepositoryImpl;
import com.example.postscommentsapp.data.repository.PostsRepositoryImpl;
import com.example.postscommentsapp.domain.repository.CommentsRepository;
import com.example.postscommentsapp.domain.repository.PostsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public PostsRepository providePostsRepository(PostsRepositoryImpl repo) {
        return repo;
    }

    @Provides
    @Singleton
    public CommentsRepository provideCommentsRepository(CommentsRepositoryImpl repo) {
        return repo;
    }
}
