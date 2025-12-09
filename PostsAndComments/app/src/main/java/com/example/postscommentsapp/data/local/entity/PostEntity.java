package com.example.postscommentsapp.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class PostEntity {

    @PrimaryKey
    private int id;

    private String title;
    private String body;

    public PostEntity(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getBody() { return body; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setBody(String body) { this.body = body; }
}
