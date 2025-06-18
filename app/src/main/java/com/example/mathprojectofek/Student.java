package com.example.mathprojectofek;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Integer> choices;
    private int id;
    private boolean isChosen;
    private DocumentReference doc;

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

    public Student(String name, ArrayList<Integer> choices, int id, boolean isChosen, DocumentReference doc) {
        this.name = name;
        this.choices = choices;
        this.id = id;
        this.isChosen = isChosen;
        this.doc = doc;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Integer> choices) {
        this.choices = choices;
    }

    public void addChoice(Integer choice) {
        choices.add(choice);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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