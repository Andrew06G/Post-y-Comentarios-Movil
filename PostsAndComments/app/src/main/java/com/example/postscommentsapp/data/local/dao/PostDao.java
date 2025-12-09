package com.example.postscommentsapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.postscommentsapp.data.local.entity.PostEntity;

import java.util.List;

@Dao
public interface PostDao {

    @Query("SELECT * FROM posts")
    List<PostEntity> getAllPosts();

    @Query("SELECT * FROM posts WHERE id = :postId LIMIT 1")
    PostEntity getPostById(int postId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPosts(List<PostEntity> posts);
}
