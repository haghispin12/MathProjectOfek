package com.example.mathprojectofek;

import android.os.Bundle;
import android.util.Log;
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
    private ArrayList<Room> rooms;
    private ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rooms);
        initView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rooms = new ArrayList<Room>();
        for (int i = 0; i < 3; i++) {
            Room room = new Room(i);
            rooms.add(room);
        }
        FirebaseFirestore.getInstance().collection("students").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                students = new ArrayList<Student>();
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    if (documentSnapshot.exists()) {
                        String name = documentSnapshot.getString("name");
                        int id = documentSnapshot.getLong("id").intValue();
                        boolean isChosen = documentSnapshot.getBoolean("isChosen");
                        ArrayList<Long> members = (ArrayList<Long>) documentSnapshot.get("members");
                        DocumentReference doc = documentSnapshot.getReference();
                        Student student = new Student(name, members, id, isChosen, doc, false);
                        students.add(student);
                    }
                }


                divideToRooms();

                removeMultipleAndAddNew();
                showRooms();
            }
        });

    }

    private void showRooms() {
        showRoom1();
        showRoom2();
        showRoom3();
    }

    private ArrayList<Student> allRoomsToOne() {
        ArrayList<Student>students1=new ArrayList<>();
        for(int i=0;i<4;i++){
            students1.add(getStudent(rooms.get(0).getMemebers().get(i)));
        }
        for(int i=0;i<4;i++){
            students1.add(getStudent(rooms.get(1).getMemebers().get(i)));
        }
        for(int i=0;i<4;i++){
            students1.add(getStudent(rooms.get(2).getMemebers().get(i)));
        }
        return students1;
    }

    private void removeMultipleAndAddNew() {
//        Room room;
//        int place;
        ArrayList<Student> allrooms = allRoomsToOne();
        for (int i=0;i<allrooms.size();i++){
            for (int j=i+1;j<allrooms.size();j++){
                if(allrooms.get(i) == allrooms.get(j)) {
                    allrooms.set(j,notInRoom());
                    allrooms.get(j).setInRoom(true);
                }
            }
        }
        OneIntoRooms(allrooms);
    }

    private void OneIntoRooms(ArrayList<Student> allrooms) {
        int j=0;
        for(int i=0;i<4;i++){
            rooms.get(0).getMemebers().set(i,allrooms.get(j).getId());
            j++;
        }
        for(int i=0;i<4;i++){
            rooms.get(1).getMemebers().set(i,allrooms.get(j).getId());
            j++;
        }
        for(int i=0;i<4;i++){
            rooms.get(2).getMemebers().set(i,allrooms.get(j).getId());
            j++;
        }
    }

//    private int getPlace(Room room, Student student) {
//        for (int i=0;i<4;i++){
//            if(getStudent(room.getMemebers().get(i))==student)
//                return i;
//        }
//        return -1;
//    }
//
//    private Room getRoom(Student student) {
//        for (int i=0;i<3;i++){
//            if(getStudent(rooms.get(i).getMemebers().get(0))==student)
//                return rooms.get(i);
//            else if(getStudent(rooms.get(i).getMemebers().get(1))==student)
//                return rooms.get(i);
//            else if(getStudent(rooms.get(i).getMemebers().get(2))==student)
//                return rooms.get(i);
//            else if(getStudent(rooms.get(i).getMemebers().get(3))==student)
//                return rooms.get(i);
//        }
//        return null;
//    }

    private void divideToRooms() {
        Student currentChooser=students.get(0);
        rooms.get(0).addStudent(currentChooser.getId());
        currentChooser.setInRoom(true);
        while(!rooms.get(0).isFull()) {

            Student selected=getStudent(currentChooser.getChoices().get(0));
            //if(!selected.isInRoom()) {
            rooms.get(0).addStudent(currentChooser.getChoices().get(0));
            selected.setInRoom(true);
            //}
//                makeRooms(rooms,students.get(0));
            //showRoom1();


            currentChooser = getStudent(currentChooser.getChoices().get(0));
        }
        currentChooser=notInRoom();
        rooms.get(1).addStudent(currentChooser.getId());
        currentChooser.setInRoom(true);
        while(!rooms.get(1).isFull()) {

            Student selected=getStudent(currentChooser.getChoices().get(0));
            //if(!selected.isInRoom()) {
            rooms.get(1).addStudent(currentChooser.getChoices().get(0));
            selected.setInRoom(true);
            //}
//                makeRooms(rooms,students.get(0));
//

//
//                friend13.setText(getStudent(rooms.get(2).getMemebers().get(0)).getName());
//                friend23.setText(getStudent(rooms.get(2).getMemebers().get(1)).getName());
//                friend33.setText(getStudent(rooms.get(2).getMemebers().get(2)).getName());
//                friend43.setText(getStudent(rooms.get(2).getMemebers().get(3)).getName());
            currentChooser = getStudent(currentChooser.getChoices().get(0));
        }
        currentChooser=notInRoom();
        rooms.get(2).addStudent(currentChooser.getId());
        currentChooser.setInRoom(true);
        while(!rooms.get(2).isFull()) {

            Student selected=getStudent(currentChooser.getChoices().get(0));
            //if(!selected.isInRoom()) {
            rooms.get(2).addStudent(currentChooser.getChoices().get(0));
            selected.setInRoom(true);
            //}
//                makeRooms(rooms,students.get(0));
//
//                    if (rooms.get(0).getMemebers().size() > 0)
//                        friend12.setText(getStudent(rooms.get(1).getMemebers().get(0)).getName());
//                    if (rooms.get(0).getMemebers().size() > 1)
//                        friend22.setText(getStudent(rooms.get(1).getMemebers().get(1)).getName());
//                    if (rooms.get(0).getMemebers().size() > 2)
//                        friend32.setText(getStudent(rooms.get(1).getMemebers().get(2)).getName());
//                    if (rooms.get(0).getMemebers().size() > 3)
//                        friend42.setText(getStudent(rooms.get(1).getMemebers().get(3)).getName());
//
            currentChooser = getStudent(currentChooser.getChoices().get(0));
        }
    }

    private void showRoom2() {
        if (rooms.get(1).getMemebers().size() > 0)
            friend12.setText(getStudent(rooms.get(1).getMemebers().get(0)).getName());
        if (rooms.get(1).getMemebers().size() > 1)
            friend22.setText(getStudent(rooms.get(1).getMemebers().get(1)).getName());
        if (rooms.get(1).getMemebers().size() > 2)
            friend32.setText(getStudent(rooms.get(1).getMemebers().get(2)).getName());
        if (rooms.get(1).getMemebers().size() > 3)
            friend42.setText(getStudent(rooms.get(1).getMemebers().get(3)).getName());
    }

    private void showRoom1() {
        if (rooms.get(0).getMemebers().size() > 0) {
            friend11.setText(getStudent(rooms.get(0).getMemebers().get(0)).getName());
        }
        if (rooms.get(0).getMemebers().size() > 1) {
            friend21.setText(getStudent(rooms.get(0).getMemebers().get(1)).getName());
        }
        if (rooms.get(0).getMemebers().size() > 2) {
            friend31.setText(getStudent(rooms.get(0).getMemebers().get(2)).getName());
        }
        if (rooms.get(0).getMemebers().size() > 3) {
            friend41.setText(getStudent(rooms.get(0).getMemebers().get(3)).getName());
        }
    }
    private void showRoom3(){
        if (rooms.get(2).getMemebers().size() > 0)
            friend13.setText(getStudent(rooms.get(2).getMemebers().get(0)).getName());
        if (rooms.get(2).getMemebers().size() > 1)
            friend23.setText(getStudent(rooms.get(2).getMemebers().get(1)).getName());
        if (rooms.get(2).getMemebers().size() > 2)
            friend33.setText(getStudent(rooms.get(2).getMemebers().get(2)).getName());
        if (rooms.get(2).getMemebers().size() > 3)
            friend43.setText(getStudent(rooms.get(2).getMemebers().get(3)).getName());
    }
    //

    private void initView() {
        friend11 = findViewById(R.id.friend11);
        friend21 = findViewById(R.id.friend21);
        friend31 = findViewById(R.id.friend31);
        friend41 = findViewById(R.id.friend41);
        friend12 = findViewById(R.id.friend12);
        friend22 = findViewById(R.id.friend22);
        friend32 = findViewById(R.id.friend32);
        friend42 = findViewById(R.id.friend42);
        friend13 = findViewById(R.id.friend13);
        friend23 = findViewById(R.id.friend23);
        friend33 = findViewById(R.id.friend33);
        friend43 = findViewById(R.id.friend43);


    }

    private Student getStudent(long id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id)
                return students.get(i);
        }
        return null;
    }
    private Student notInRoom(){
        for(int i = 0;i<students.size();i++){
            if(!students.get(i).isInRoom()) {
                return students.get(i);
            }
        }
        return null;
    }

//    private void makeRooms(ArrayList<Room>rooms,Student currentStudent){
//        long inTheRoom;
//        //scan all rooms
//        for(int i=0;i<rooms.size();i++){
//            // if the room is not full
//            while (!(rooms.get(i).isFull())){
//                inTheRoom=studentToRoom(rooms.get(i),currentStudent);
//                Log.d("testLoop1","loop1");
//                //if not all choises  is already in rooms
//                if(inTheRoom!=-1)
//                    currentStudent=getStudent(inTheRoom);
//                else {
//                    for(int j=0;j<students.size();j++){
//                        if(!students.get(j).isInRoom())
//                            currentStudent=students.get(j);
//                    }
//                }
//            }
//            Log.d("testLoop2","loop2");
//        }
//        Log.d("testLoop3","loop3");
//    }
//    private long studentToRoom (Room room,Student currentStudent){
//        for (int i=0;i<currentStudent.getChoices().size();i++) {
//            Log.d("testLoop4","studentToRoom before");
//            long selectedId=currentStudent.getChoices().get(i);
//            Student selectedStudent=getStudent(selectedId);
//            //if this student do not have a room
//            if (!selectedStudent.isInRoom()) {
//                Log.d("testLoop4","studentToRoom after");
//                // add this student to room
//                room.addStudent(selectedId);
//                selectedStudent.setInRoom(true);
//                return selectedId;
//            }
//        }
//        return -1;
//    }
}