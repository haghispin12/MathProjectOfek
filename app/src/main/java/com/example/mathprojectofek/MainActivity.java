package com.example.mathprojectofek;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button rate;
    private int num1;
    private int num2;
    private int score;
    MainViewModel viewModelMain;
    String userName;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            int myrate = result.getData().getIntExtra("rate", -1);
            Toast.makeText(MainActivity.this,myrate+"",Toast.LENGTH_SHORT).show();
            int showAllUsers=result.getData().getIntExtra("showUsers",-1);
        }
    });
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
        SharedPreferences sh=getSharedPreferences("sharedPref",MODE_PRIVATE);
        String s=sh.getString("name",userName);
        name.setText(s);
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
        rate=findViewById(R.id.rate);
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
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
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
                FragmentTransaction trans=getSupportFragmentManager().beginTransaction();
                trans.add(R.id.framelayout,new fragment_showusers());
                trans.commit();
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, rateActivity.class);
                activityResultLauncher.launch(intent);

            }
        });
    }
    /*

     */


    }
