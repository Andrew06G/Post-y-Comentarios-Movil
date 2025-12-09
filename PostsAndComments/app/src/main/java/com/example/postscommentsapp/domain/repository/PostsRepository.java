package com.example.postscommentsapp.domain.repository;

import com.example.postscommentsapp.domain.model.Post;

import java.util.List;

public interface PostsRepository {

    List<Post> getPosts() throws Exception;

    List<Post> searchPosts(String query) throws Exception;
}
