package com.example.postscommentsapp.data.remote;

import com.example.postscommentsapp.data.remote.dto.PostDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholderService {

    @GET("posts")
    Call<List<PostDto>> getPosts();
}
