package com.example.postscommentsapp.domain.usecase;

import com.example.postscommentsapp.domain.model.Post;
import com.example.postscommentsapp.domain.repository.PostsRepository;

import java.util.List;

import javax.inject.Inject;

public class GetPostsUseCase {

    private final PostsRepository repository;

    @Inject
    public GetPostsUseCase(PostsRepository repository) {
        this.repository = repository;
    }

    public List<Post> execute() throws Exception {
        return repository.getPosts();
    }
}
