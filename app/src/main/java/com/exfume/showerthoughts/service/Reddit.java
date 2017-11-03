package com.exfume.showerthoughts.service;

import android.util.Log;

import com.exfume.showerthoughts.deserialization.RedditDeserializer;
import com.exfume.showerthoughts.interceptor.JsonInterceptor;
import com.exfume.showerthoughts.model.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reddit implements Callback<List<Post>> {

    private static final String BASE_URL = "https://www.reddit.com/";
    private RedditService service;
    private RedditCallback callback;

    public Reddit(String subreddit){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(List.class,new RedditDeserializer())
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new JsonInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL+"r/"+subreddit+"/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.service = retrofit.create(RedditService.class);
    }

    public void random(RedditCallback callback){
        this.callback = callback;
        this.service.random().enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if(response.isSuccessful()) {
            this.callback.onSuccess(response.body());
        } else {
            this.callback.onFailure(new Throwable("No successful response from the server!"));
        }
    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable t) {
        this.callback.onFailure(t);
    }


    public interface RedditCallback {
        void onSuccess(List<Post> posts);
        void onFailure(Throwable t);
    }

}
