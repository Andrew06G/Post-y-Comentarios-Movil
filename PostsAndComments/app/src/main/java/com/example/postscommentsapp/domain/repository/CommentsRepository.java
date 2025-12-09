package com.example.postscommentsapp.domain.repository;

import com.example.postscommentsapp.domain.model.Comment;

import java.util.List;

public interface CommentsRepository {

    List<Comment> getCommentsByPost(int postId);

    void insertComment(Comment comment);
}
