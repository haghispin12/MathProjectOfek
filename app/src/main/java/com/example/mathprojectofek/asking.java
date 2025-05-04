package com.example.mathprojectofek;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<String> students = new ArrayList<String>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EdgeToEdge.enable(this);
        initView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CollectionReference studentsRef = db.collection("students");
        studentsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Access specific fields
                        String name = document.getString("name");
                        students.add(name);
                    }
                    Log.d("","");
                    loadStudents(students);
                }
            }
        });

    }


    private void initView() {
        Name = findViewById(R.id.Name);
        Spinner1 = findViewById(R.id.Spinner1);
        Spinner2 = findViewById(R.id.Spinner2);
        Spinner3 = findViewById(R.id.Spinner3);
        Spinner4 = findViewById(R.id.Spinner4);
        Spinner5 = findViewById(R.id.Spinner5);
        String name;
    }

    private void loadStudents(ArrayList<String>students) {
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,students);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Name.setAdapter(adapter);
        Name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Spinner1.setAdapter(adapter);
        Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice1 = parent.getItemAtPosition(position).toString();
                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        Spinner2.setAdapter(adapter);
        Spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice2 = parent.getItemAtPosition(position).toString();
                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        Spinner3.setAdapter(adapter);
        Spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice3 = parent.getItemAtPosition(position).toString();
                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        Spinner4.setAdapter(adapter);
        Spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice4 = parent.getItemAtPosition(position).toString();
                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        Spinner5.setAdapter(adapter);
        Spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice5 = parent.getItemAtPosition(position).toString();
                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
}



//    private void initView(ArrayList<String>students) {
//        Name=findViewById(R.id.Name);
//        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,students);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Name.setAdapter(adapter);
//        Name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selectedItem = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
//        Spinner1=findViewById(R.id.Spinner1);
//        Spinner1.setAdapter(adapter);
//        Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String choice1 = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
//        Spinner2=findViewById(R.id.Spinner2);
//        Spinner2.setAdapter(adapter);
//        Spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String choice2 = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
//        Spinner3=findViewById(R.id.Spinner3);
//        Spinner3.setAdapter(adapter);
//        Spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String choice3 = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
//        Spinner4=findViewById(R.id.Spinner4);
//        Spinner4.setAdapter(adapter);
//        Spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String choice4 = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
//        Spinner5=findViewById(R.id.Spinner5);
//        Spinner5.setAdapter(adapter);
//        Spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String choice5 = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(asking.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });
//    }
//}