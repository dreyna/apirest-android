package com.example.api_rest.interface1;

import com.example.api_rest.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {
    @GET("posts")
    Call<List<Post>> getPost();
}
