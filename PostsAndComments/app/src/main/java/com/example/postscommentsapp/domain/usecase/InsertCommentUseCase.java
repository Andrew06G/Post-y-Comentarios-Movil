package com.example.postscommentsapp.domain.usecase;

import com.example.postscommentsapp.domain.model.Comment;
import com.example.postscommentsapp.domain.repository.CommentsRepository;

import javax.inject.Inject;

public class InsertCommentUseCase {

    private final CommentsRepository repository;

    @Inject
    public InsertCommentUseCase(CommentsRepository repository) {
        this.repository = repository;
    }

    public void execute(Comment comment) {
        repository.insertComment(comment);
    }
}
