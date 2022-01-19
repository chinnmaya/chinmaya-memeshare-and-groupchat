package com.example.chinmayagroupchatandmemeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.viewholder>{
    List<ImgPojo> list;
    Context context;

    public ImageAdapter(List<ImgPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull

    @Override
    public ImageAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.imagesample,parent,false);
        return new viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.viewholder holder, int position) {
        ImgPojo imgPojo=list.get(position);

        holder.textView2.setText(imgPojo.getPost());


        Glide.with(context).load(list.get(position).getUrl()).into(holder.img);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView textView2,textView3;
        ImageView img;

        public viewholder(@NonNull  View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.imageView);
            textView2=itemView.findViewById(R.id.textView2);


        }
    }
}
