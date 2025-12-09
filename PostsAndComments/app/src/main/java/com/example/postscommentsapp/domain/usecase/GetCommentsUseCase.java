package com.example.postscommentsapp.domain.usecase;

import com.example.postscommentsapp.domain.model.Comment;
import com.example.postscommentsapp.domain.repository.CommentsRepository;

import java.util.List;

import javax.inject.Inject;

public class GetCommentsUseCase {

    private final CommentsRepository repository;

    @Inject
    public GetCommentsUseCase(CommentsRepository repository) {
        this.repository = repository;
    }

    public List<Comment> execute(int postId) {
        return repository.getCommentsByPost(postId);
    }
}
