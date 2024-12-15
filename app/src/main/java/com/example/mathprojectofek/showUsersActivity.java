package com.example.mathprojectofek;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class showUsersActivity extends AppCompatActivity {
    private RecyclerView ShowFruits;
    //private RecyclerView ShowUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        showFruits();
    }
    private void initView() {
        ShowFruits=findViewById(R.id.rcShowUsers);
    }
        public void showFruits(){
        ArrayList<Fruit> fruits=new ArrayList<>();
        fruits.add(new Fruit("banana",R.drawable.banana));
        fruits.add(new Fruit("apple",R.drawable.apple));
        fruits.add(new Fruit("fruits",R.drawable.fruits));
        fruits.add(new Fruit("grapes",R.drawable.grapes));
        fruits.add(new Fruit("lemon",R.drawable.lemon));
        fruits.add(new Fruit("orange",R.drawable.orange));
        MyFruitsAdapter myFruitsAdapter=new MyFruitsAdapter(fruits, new MyFruitsAdapter.OnItemClicklListener() {
            @Override
            public void OnItemClick(Fruit item) {
                Toast.makeText(showUsersActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        ShowFruits.setLayoutManager(new LinearLayoutManager(this));
        ShowFruits.setAdapter(myFruitsAdapter);
        ShowFruits.setHasFixedSize(true);
    }


}