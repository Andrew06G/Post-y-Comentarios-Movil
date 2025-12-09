package com.example.postscommentsapp.data.remote;

import com.example.postscommentsapp.data.remote.dto.PostDto;

import java.io.IOException;
import java.util.List;

public class RemoteDataSource {

    private final JSONPlaceholderService api;

    public RemoteDataSource(JSONPlaceholderService api) {
        this.api = api;
    }

    public List<PostDto> fetchPosts() throws IOException {
        return api.getPosts().execute().body();
    }

}
