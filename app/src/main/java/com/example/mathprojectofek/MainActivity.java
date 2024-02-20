package com.example.mathprojectofek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button challenge;
    private Button double20;
    private Button doubleBoard;
    private TextView tvNum1;
    private TextView tvNum2;
    private EditText answer;
    private Button check;
    private Button save;
    private Button showAll;
    private TextView name;
    private int num1;
    private int num2;
    MainViewModel viewModelMain;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        viewModelMain=new ViewModelProvider(this).get(MainViewModel.class);
        viewModelMain.vNum1.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer num) {
                tvNum1.setText(num+"");
            }
        });
        viewModelMain.vNum2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer num) {
                tvNum2.setText(num+"");
            }
        });
        Intent intent=getIntent();
        String userName=intent.getStringExtra("userName");
        name.setText(userName);
    }
    public void initView() {
        challenge = findViewById(R.id.challenge);
        double20 = findViewById(R.id.double20);
        doubleBoard = findViewById(R.id.doubleBoard);
        tvNum1 = findViewById(R.id.tvnum1);
        tvNum2 = findViewById(R.id.tvnum2);
        answer = findViewById(R.id.answer);
        check = findViewById(R.id.check);
        save = findViewById(R.id.save);
        showAll = findViewById(R.id.showAll);
        name=findViewById(R.id.name);
        challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelMain.vChallenge();
            }
        });
        double20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelMain.vTill20();
            }
        });
        doubleBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModelMain.vDoubleBoard();
            }
        });
        //Toast.makeText(this, "succes", Toast.LENGTH_SHORT).show();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewModelMain.ex.checkAnswer(answer.getText().toString()))
                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    /*

     */


    }
