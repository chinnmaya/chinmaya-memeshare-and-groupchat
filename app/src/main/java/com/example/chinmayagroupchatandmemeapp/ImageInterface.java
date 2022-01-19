package com.example.chinmayagroupchatandmemeapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageInterface {
    @GET("chinmaya_image.php")
    Call<List<ImgPojo>> getposts();
}
