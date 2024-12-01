package com.example.mathprojectofek;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyFruitsAdapter extends RecyclerView.Adapter<MyFruitsAdapter.MyViewHolder>{
    public interface OnItemClicklListener {
        void OnItemClick(Fruit item);
    }
    private ArrayList<Fruit>fruits;
    private OnItemClicklListener listener;

    public MyFruitsAdapter(ArrayList<Fruit> fruits, OnItemClicklListener listener) {
        this.fruits = fruits;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyFruitsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFruitsAdapter.MyViewHolder holder, int position) {
        holder.bind(fruits.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView fruitName;
        ImageView fruitImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitName=itemView.findViewById(R.id.fruitName);
            fruitImage = itemView.findViewById(R.id.fruitImage);
        }

        public void bind(final Fruit item, final OnItemClicklListener listener) {
            fruitName.setText(item.getName());
            fruitImage.setImageResource(item.getDrawble());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.OnItemClick(item);
                }
            });
        }
    }
}
