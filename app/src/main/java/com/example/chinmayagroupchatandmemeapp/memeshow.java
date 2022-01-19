package com.example.chinmayagroupchatandmemeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class memeshow extends AppCompatActivity {
    ImageInterface imageInterface;
    RecyclerView recyclerView1;
    ImageView logout,upload,chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memeshow);
        recyclerView1=findViewById(R.id.recyclerView);
        logout=findViewById(R.id.imageView7);
        chat=findViewById(R.id.imageView4);
        upload=findViewById(R.id.imageView6);
        imageInterface=Retrofitinstance.getRetrofit().create(ImageInterface.class);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(memeshow.this,homeactivity.class));
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(memeshow.this,upload.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(memeshow.this,login.class));
            }
        });

        imageInterface.getposts().enqueue(new Callback<List<ImgPojo>>() {
            @Override
            public void onResponse(Call<List<ImgPojo>> call, Response<List<ImgPojo>> response) {
                if(response.body().size()>0){
                    List<ImgPojo>pojoList=response.body();

                    ImageAdapter imageAdapter=new ImageAdapter(pojoList,memeshow.this);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(memeshow.this);
                    recyclerView1.setLayoutManager(linearLayoutManager);
                    recyclerView1.setAdapter(imageAdapter);
                    Toast.makeText(memeshow.this, "list is not empty", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(memeshow.this, "list is empty", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<ImgPojo>> call, Throwable t) {
                Toast.makeText(memeshow.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}