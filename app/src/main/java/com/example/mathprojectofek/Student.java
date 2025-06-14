package com.example.mathprojectofek;

import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Student>choices;

    public Student(String name, ArrayList<Student> choices) {
        this.name = name;
        this.choices = choices;
    }
    public Student(String name) {
        this.name = name;
        ArrayList<String>choices=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Student> choices) {
        this.choices = choices;
    }
}
