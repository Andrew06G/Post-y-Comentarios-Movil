package com.example.postscommentsapp.data.mapper;

import com.example.postscommentsapp.data.local.entity.CommentEntity;
import com.example.postscommentsapp.domain.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    public static Comment toDomain(CommentEntity entity) {
        return new Comment(
                entity.getId(),
                entity.getPostId(),
                entity.getComment(),
                entity.getCreatedAt()
        );
    }

    public static CommentEntity toEntity(Comment comment) {
        return new CommentEntity(
                comment.getId(),
                comment.getPostId(),
                comment.getComment(),
                comment.getCreatedAt()
        );
    }

    public static List<Comment> toDomainList(List<CommentEntity> list) {
        List<Comment> result = new ArrayList<>();
        for (CommentEntity e : list) {
            result.add(toDomain(e));
        }
        return result;
    }
}
