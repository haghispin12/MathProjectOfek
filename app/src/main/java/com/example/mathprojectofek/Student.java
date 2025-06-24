package com.example.mathprojectofek;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Long> choices;
    private long id;
    private boolean isChosen;
    private DocumentReference doc;
    private boolean isInRoom;

    //    public Integer(String name, ArrayList<Integer> choices) {
//        this.name = name;
//        this.choices = choices;
//    }
    public Student(String name) {
        this.name = name;
        choices = new ArrayList<>();
    }
    public Student(){
        name=new String();
        choices=new ArrayList<>();
    }

    public Student(String name, ArrayList<Long> choices, long id, boolean isChosen, DocumentReference doc, boolean isInRoom) {
        this.name = name;
        this.choices = choices;
        this.id = id;
        this.isChosen = isChosen;
        this.doc = doc;
        this.isInRoom=isInRoom;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Long> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Long> choices) {
        this.choices = choices;
    }

    public void addChoice(long choice) {
        choices.add(choice);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isInRoom() {
        return isInRoom;
    }

    public void setInRoom(boolean inRoom) {
        isInRoom = inRoom;
    }

    public boolean isChosen() {
        return isChosen;
    }
    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public DocumentReference getDoc() {
        return doc;
    }
}