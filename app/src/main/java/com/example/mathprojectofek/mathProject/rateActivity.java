package com.example.mathprojectofek.mathProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.mathprojectofek.R;

public class rateActivity extends AppCompatActivity {
    private SeekBar skRate;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        initView();

    }
    public void initView(){
        skRate=findViewById(R.id.skRate);
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inn=new Intent();
                inn.putExtra("rate",skRate.getProgress());
                setResult(RESULT_OK,inn);
                finish();
            }
        });
    }
}