package com.example.postscommentsapp.di;

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
