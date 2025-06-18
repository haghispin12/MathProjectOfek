package com.example.mathprojectofek;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
import com.google.firebase.firestore.Source;

import java.util.ArrayList;

public class asking extends AppCompatActivity {
    private Spinner Name;
    private Spinner Spinner1;
    private Spinner Spinner2;
    private Spinner Spinner3;
    private Spinner Spinner4;
    private Button save;
    private ArrayList<Student>students;
    private ArrayList<String> names;
    private ArrayList<Integer>choices=new ArrayList<>();
    private Student currentStudent;
    boolean isFirst1 = true;
    boolean isFirst2 = true;
    boolean isFirst3 = true;
    boolean isFirst4 = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asking);
        ArrayList<String> names = new ArrayList<String>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EdgeToEdge.enable(this);
        initView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        CollectionReference studentsRef = db.collection("names");
        FirebaseFirestore.getInstance().collection("students").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                asking.this.names = new ArrayList<>();
                asking.this.students = new ArrayList<>();
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("name");
                        int id = documentSnapshot.getLong("id").intValue();
                        boolean isChosen = documentSnapshot.getBoolean("isChosen");
                        ArrayList<Integer> members = (ArrayList<Integer>) documentSnapshot.get("members");
                        names.add(name);
                        DocumentReference doc = documentSnapshot.getReference();
                        students.add(new Student(name, members, id, isChosen, doc));
                    }
                }
                loadStudents(names);
            }
        });
    }


        private void initView () {
            Name = findViewById(R.id.Name);
            Spinner1 = findViewById(R.id.Spinner1);
            Spinner2 = findViewById(R.id.Spinner2);
            Spinner3 = findViewById(R.id.Spinner3);
            Spinner4 = findViewById(R.id.Spinner4);
            save = findViewById(R.id.save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < 4; i++) {
                        currentStudent.addChoice(choices.get(i));
                    }
                    currentStudent.getDoc().update("members",currentStudent.getChoices());
                    currentStudent.getDoc().update("isChosen",true);
                    Intent intent = new Intent(asking.this,rooms.class);
                    startActivity(intent);
                }
            });
        }

//    private boolean different(ArrayList<Integer> ch) {
//        for()
//    }

    private void loadStudents (ArrayList < String > names) {


            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Name.setAdapter(adapter);
            Name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    currentStudent = getStudent(adapterView.getItemAtPosition(i).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            Spinner1.setAdapter(adapter);

            Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(!isFirst1) {
                        Log.d("items", parent.getItemAtPosition(position).toString());
                        choices.add(getId(parent.getItemAtPosition(position).toString()));
                    }else{
                        isFirst1=false;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            Spinner2.setAdapter(adapter);
            Spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!isFirst2) {
                        choices.add(getId(parent.getItemAtPosition(position).toString()));

                    }else{
                        isFirst2=false;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            Spinner3.setAdapter(adapter);
            Spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!isFirst3) {
                        choices.add(getId(parent.getItemAtPosition(position).toString()));
                    }else {
                        isFirst3=false;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            Spinner4.setAdapter(adapter);
            Spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!isFirst4) {
                        choices.add(getId(parent.getItemAtPosition(position).toString()));
                    }else{
                        isFirst4=false;

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        private Student getStudent (String string){
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getName().equals(string))
                    return students.get(i);
            }
            return null;
        }

        private int getId (String string){
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getName().equals(string))
                    return students.get(i).getId();
            }
            return -1;
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