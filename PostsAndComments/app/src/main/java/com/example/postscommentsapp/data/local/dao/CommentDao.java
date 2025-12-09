package com.example.postscommentsapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.postscommentsapp.data.local.entity.CommentEntity;

import java.util.List;

@Dao
public interface CommentDao {

    @Query("SELECT * FROM comments WHERE postId = :postId ORDER BY createdAt DESC")
    List<CommentEntity> getCommentsByPost(int postId);

    @Insert
    void insertComment(CommentEntity comment);
}
