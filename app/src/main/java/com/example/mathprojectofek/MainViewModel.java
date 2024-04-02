package com.example.mathprojectofek;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    MutableLiveData <Integer> vNum1;
    MutableLiveData <Integer> vNum2;
    Exercise ex;
    User user;
    int bet;
    public MainViewModel(){
        vNum1=new MutableLiveData<>();
        vNum2=new MutableLiveData<>();
        ex=new Exercise();
        user=new User();
    }
    public void setName(String name){
        user.setName(name);
    }
    public void setRate(int rate){
        user.setRate(rate);
    }
    public void addScore(int score){
        int sc= user.getScore()+score;
        user.setScore(sc);
    }
    public void minusScore(int score){
        int sc=user.getScore()-score;
        user.setScore(sc);
    }
    public int getScore(){
        return user.getScore();
    }
    public void vChallenge(){
        ex.generateNumsChallenge();
        vNum1.setValue(ex.getNum1());
        vNum2.setValue(ex.getNum2());
        bet=20;
    }
    public void vTill20(){
        ex.generateNumsTill20();
        vNum1.setValue(ex.getNum1());
        vNum2.setValue(ex.getNum2());
        bet=15;
    }
    public void vDoubleBoard(){
        ex.generateNumsDoubleBoard();
       vNum1.setValue(ex.getNum1());
       vNum2.setValue(ex.getNum2());
       bet=10;
    }
    public boolean vCheck(String s){
        return ex.checkAnswer(s);
    }

}
