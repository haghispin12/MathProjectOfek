package com.example.mathprojectofek;

import java.util.ArrayList;

public class Room {
    private int num;
    private ArrayList<Long> memebers;

    public Room(int num) {
        this.num = num;
        this.memebers = new ArrayList<>();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<Long> getMemebers() {
        return memebers;
    }

    public void setMemebers(ArrayList<Long> memebers) {
        this.memebers = memebers;
    }

    public void addStudent(long id){
        memebers.add(id);
    }
//    public boolean isInRoom(long id){
//        for (int i=0;i<memebers.size();i++){
//            if(memebers.get(i)==id) {
//                return true;
//            }
//        }
//        return false;
//    }
    public boolean isFull(){
        if(memebers.size()==4)
            return true;
        return false;
    }
}
