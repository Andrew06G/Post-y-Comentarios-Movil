package com.example.postscommentsapp.di;

import android.content.Context;

import androidx.room.Room;

import com.example.postscommentsapp.data.local.dao.CommentDao;
import com.example.postscommentsapp.data.local.dao.PostDao;
import com.example.postscommentsapp.data.local.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public AppDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                        context,
                        AppDatabase.class,
                        "posts_comments_db"
                ).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public PostDao providePostDao(AppDatabase db) {
        return db.postDao();
    }

    @Provides
    @Singleton
    public CommentDao provideCommentDao(AppDatabase db) {
        return db.commentDao();
    }
}
