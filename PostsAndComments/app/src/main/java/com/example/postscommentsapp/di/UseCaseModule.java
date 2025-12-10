package com.example.postscommentsapp.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class UseCaseModule {

    @Provides
    public GetPostsUseCase provideGetPostsUseCase(PostsRepository repo) {
        return new GetPostsUseCase(repo);
    }

    @Provides
    public SearchPostsUseCase provideSearchPostsUseCase(PostsRepository repo) {
        return new SearchPostsUseCase(repo);
    }

    @Provides
    public GetCommentsUseCase provideGetCommentsUseCase(CommentsRepository repo) {
        return new GetCommentsUseCase(repo);
    }

    @Provides
    public InsertCommentUseCase provideInsertCommentUseCase(CommentsRepository repo) {
        return new InsertCommentUseCase(repo);
    }
}
