package com.example.chinmayagroupchatandmemeapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("chinmaya_read.php")
    Call<List<PostPojo>> getposts();
}
