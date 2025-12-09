package com.example.postscommentsapp.domain.usecase;

import com.example.postscommentsapp.domain.model.Post;
import com.example.postscommentsapp.domain.repository.PostsRepository;

import java.util.List;

import javax.inject.Inject;

public class SearchPostsUseCase {

    private final PostsRepository repository;

    @Inject
    public SearchPostsUseCase(PostsRepository repository) {
        this.repository = repository;
    }

    public List<Post> execute(String query) throws Exception {
        return repository.searchPosts(query);
    }
}
