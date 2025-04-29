package com.example.mathprojectofek;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class asking extends AppCompatActivity {
    private Spinner Name;
    private Spinner Spinner1;
    private Spinner Spinner2;
    private Spinner Spinner3;
    private Spinner Spinner4;
    private Spinner Spinner5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asking);
        EdgeToEdge.enable(this);
        ArrayList<String>students=new ArrayList<>();
        FirebaseFirestore.getInstance().collection("students").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    if(documentSnapshot.exists()){
                        String name=documentSnapshot.getString("name");
                        students.add(name);
                    }
                }
            }
        });
        initView(students);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @SuppressLint("WrongViewCast")
    private void initView(ArrayList<String>students) {
        Name=findViewById(R.id.Name);
        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,students);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Name.setAdapter(adapter);
//        Name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //String selectedItem = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
        Spinner1=findViewById(R.id.Spinner1);
        Spinner2=findViewById(R.id.Spinner2);
        Spinner3=findViewById(R.id.Spinner3);
        Spinner4=findViewById(R.id.Spinner4);
        Spinner5=findViewById(R.id.Spinner5);
    }
}