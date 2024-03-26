package com.example.mathprojectofek;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 .
 */
public class fragment_showusers extends Fragment {
    private EditText user;
    private Button addPicture;
    private Button addUser;

    MainViewModel mainViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView(View view) {
        user =view.findViewById(R.id.user);
        addPicture=view.findViewById(R.id.addPicture);
        addUser=view.findViewById(R.id.addUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_showusers, container, false);

        initView(view);
        return  view;
    }
}