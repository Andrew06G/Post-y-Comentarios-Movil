package com.example.postscommentsapp.data.repository;

import com.example.postscommentsapp.data.local.dao.CommentDao;
import com.example.postscommentsapp.data.local.entity.CommentEntity;
import com.example.postscommentsapp.data.mapper.CommentMapper;
import com.example.postscommentsapp.domain.model.Comment;
import com.example.postscommentsapp.domain.repository.CommentsRepository;

import java.util.List;

import javax.inject.Inject;

public class CommentsRepositoryImpl implements CommentsRepository {

    private final CommentDao commentDao;

    @Inject
    public CommentsRepositoryImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public List<Comment> getCommentsByPost(int postId) {
        return CommentMapper.toDomainList(commentDao.getCommentsByPost(postId));
    }

    @Override
    public void insertComment(Comment comment) {
        commentDao.insertComment(
                new CommentEntity(
                        0,
                        comment.getPostId(),
                        comment.getComment(),
                        comment.getCreatedAt()
                )
        );
    }
}
