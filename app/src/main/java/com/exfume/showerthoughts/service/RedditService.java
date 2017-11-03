package com.exfume.showerthoughts.service;

import com.exfume.showerthoughts.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RedditService {

    @GET("/")
    Call<List<Post>> index();

    @GET("random")
    Call<List<Post>> random();

}
