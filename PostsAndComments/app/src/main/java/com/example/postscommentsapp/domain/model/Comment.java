package com.example.postscommentsapp.domain.model;

public class Comment {
    private int id;
    private int postId;
    private String comment;
    private long createdAt;

    public Comment(int id, int postId, String comment, long createdAt) {
        this.id = id;
        this.postId = postId;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public int getPostId() { return postId; }
    public String getComment() { return comment; }
    public long getCreatedAt() { return createdAt; }

    public void setId(int id) { this.id = id; }
    public void setPostId(int postId) { this.postId = postId; }
    public void setComment(String comment) { this.comment = comment; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
