package com.example.postscommentsapp.di;

import com.example.postscommentsapp.domain.usecase.GetPostsUseCase;
import com.example.postscommentsapp.domain.usecase.SearchPostsUseCase;
import com.example.postscommentsapp.domain.usecase.GetCommentsUseCase;
import com.example.postscommentsapp.domain.usecase.InsertCommentUseCase;
import com.example.postscommentsapp.domain.repository.PostsRepository;
import com.example.postscommentsapp.domain.repository.CommentsRepository;

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
