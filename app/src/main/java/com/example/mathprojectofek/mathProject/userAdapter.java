package com.example.mathprojectofek.mathProject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathprojectofek.R;

import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.MyViewHolder> {
    public interface OnItemClicklListener1 {
        void OnItemClick(User item);
    }
    private ArrayList<User> users;
    private OnItemClicklListener1 listener;

    public userAdapter(ArrayList<User> users, OnItemClicklListener1 listener) {
        this.users = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public userAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new userAdapter.MyViewHolder(view);
    }


    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(users.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        TextView userScore;
        ImageView userImage;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.name);
            userImage = itemView.findViewById(R.id.image);
            userScore=itemView.findViewById(R.id.score);
        }

        public void bind(final User item, final OnItemClicklListener1 listener) {
            userName.setText(item.getName());
            userScore.setText(item.getScore()+"");
            userImage.setImageBitmap(item.getBitmap());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.OnItemClick(item);
                }
            });
        }
    }
}
