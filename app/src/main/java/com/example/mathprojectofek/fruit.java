package com.example.mathprojectofek;

public class fruit {
    private String name;
    private int drawble;

    public fruit(int drawble,String name) {
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
