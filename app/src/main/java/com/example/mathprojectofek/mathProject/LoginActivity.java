package com.example.mathprojectofek.mathProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mathprojectofek.R;

public class LoginActivity extends AppCompatActivity {
    private EditText userName;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();

    }
    public void initview(){
        userName=findViewById(R.id.userName);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("userName",userName.getText().toString());
                startActivity(intent);
                SharedPreferences sharedPreferences = getSharedPreferences("SharedPref",MODE_PRIVATE);
                SharedPreferences.Editor myEdit=sharedPreferences.edit();
                myEdit.putString("name",userName.getText().toString());
                myEdit.apply();
            }
        });
    }
}