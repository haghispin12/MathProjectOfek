package com.example.mathprojectofek.mathProject;

public class Fruit {
    private String name;
    private int drawble;




    public Fruit(String name,int drawble) {
        this.drawble = drawble;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public int getDrawble() {
        return drawble;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDrawble(int drawble) {
        this.drawble = drawble;
    }
}
