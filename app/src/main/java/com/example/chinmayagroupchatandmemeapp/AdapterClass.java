package com.example.chinmayagroupchatandmemeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.viewholder> {
    List<PostPojo> list;
    Context context;

    public AdapterClass(List<PostPojo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chatsample,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.viewholder holder, int position) {
        PostPojo pojo=list.get(position);
        holder.textView2.setText(pojo.getName());
        holder.textView3.setText(pojo.getMessage());

        //Glide.with(context).load(list.get(position).getUrl()).into(holder.img);



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
            textView3=itemView.findViewById(R.id.txtMessages);
            //img=itemView.findViewById(R.id.imageView);
            textView2=itemView.findViewById(R.id.nameee);


        }
    }
}

