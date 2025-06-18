package com.example.mathprojectofek;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class rooms extends AppCompatActivity {
    private TextView friend11;
    private TextView friend21;
    private TextView friend31;
    private TextView friend41;
    private TextView friend12;
    private TextView friend22;
    private TextView friend32;
    private TextView friend42;
    private TextView friend13;
    private TextView friend23;
    private TextView friend33;
    private TextView friend43;
    private ArrayList<Room>rooms;
    private ArrayList<Student>students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        initView();
        setContentView(R.layout.activity_rooms);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FirebaseFirestore.getInstance().collection("students").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                students=new ArrayList<Student>();
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("name");
                        int id = documentSnapshot.getLong("id").intValue();
                        boolean isChosen = documentSnapshot.getBoolean("isChosen");
                        ArrayList<Integer> members = (ArrayList<Integer>) documentSnapshot.get("members");
                        DocumentReference doc = documentSnapshot.getReference();
                        students.add(new Student(name, members, id, isChosen, doc));
                    }
                }
            }
        });
    }

    private void initView() {
        friend11=findViewById(R.id.friend11);
        friend21=findViewById(R.id.friend21);
        friend31=findViewById(R.id.friend31);
        friend41=findViewById(R.id.friend41);
        friend12=findViewById(R.id.friend12);
        friend22=findViewById(R.id.friend22);
        friend32=findViewById(R.id.friend32);
        friend42=findViewById(R.id.friend42);
        friend13=findViewById(R.id.friend13);
        friend23=findViewById(R.id.friend23);
        friend33=findViewById(R.id.friend33);
        friend43=findViewById(R.id.friend43);
        rooms.get(0).addStudent(students.get(0).getId());
        Student currentStudent = students.get(0);
        for(int i=0;i<rooms.size();i++){
            while (!(rooms.get(i).isFull())){
                for (int j=0;j<currentStudent.getChoices().size();j++) {
                    if (!(rooms.get(i).isInRoom(currentStudent.getChoices().get(j))))
                        rooms.get(i).addStudent(currentStudent.getChoices().get(j));
                    currentStudent = getStudent(currentStudent.getChoices().get(j));
                }
            }
        }
        friend11.setText(getStudent(rooms.get(0).getMemebers().get(0)).getName());
        friend21.setText(getStudent(rooms.get(0).getMemebers().get(1)).getName());
        friend31.setText(getStudent(rooms.get(0).getMemebers().get(2)).getName());
        friend41.setText(getStudent(rooms.get(0).getMemebers().get(3)).getName());

        friend12.setText(getStudent(rooms.get(1).getMemebers().get(0)).getName());
        friend22.setText(getStudent(rooms.get(1).getMemebers().get(1)).getName());
        friend32.setText(getStudent(rooms.get(1).getMemebers().get(2)).getName());
        friend42.setText(getStudent(rooms.get(1).getMemebers().get(3)).getName());

        friend13.setText(getStudent(rooms.get(2).getMemebers().get(0)).getName());
        friend23.setText(getStudent(rooms.get(2).getMemebers().get(1)).getName());
        friend33.setText(getStudent(rooms.get(2).getMemebers().get(2)).getName());
        friend43.setText(getStudent(rooms.get(2).getMemebers().get(3)).getName());
    }
    private Student getStudent(int id){
        for (int i=0;i<students.size();i++){
            if(students.get(i).getId()==id)
                return students.get(i);
        }
        return null;
    }
}