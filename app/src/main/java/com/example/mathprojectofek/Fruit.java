package com.example.mathprojectofek;

import androidx.recyclerview.widget.RecyclerView;

public class Fruit {
    private String name;
    private int drawble;




    public Fruit(int drawble, String name) {
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
