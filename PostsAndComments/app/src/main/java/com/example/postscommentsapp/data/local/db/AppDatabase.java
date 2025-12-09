package com.example.postscommentsapp.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.postscommentsapp.data.local.dao.CommentDao;
import com.example.objects.postscommentsapp.data.local.dao.PostDao;
import com.example.postscommentsapp.data.local.entity.CommentEntity;
import com.example.postscommentsapp.data.local.entity.PostEntity;

@Database(
        entities = {PostEntity.class, CommentEntity.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PostDao postDao();
    public abstract CommentDao commentDao();
}
