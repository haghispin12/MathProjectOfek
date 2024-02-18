package com.example.mathprojectofek;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    MutableLiveData <Integer> vNum1;
    MutableLiveData <Integer> vNum2;
    Exercise ex;
    public MainViewModel(){
        vNum1=new MutableLiveData<>();
        vNum2=new MutableLiveData<>();
        ex=new Exercise();
    }
    public void vChallenge(){
        ex.generateNumsChallenge();
        vNum1.setValue(ex.getNum1());
        vNum2.setValue(ex.getNum2());
    }
    public void vTill20(){
        ex.generateNumsTill20();
        vNum1.setValue(ex.getNum1());
        vNum2.setValue(ex.getNum2());
    }
    public void vDoubleBoard(){
        ex.generateNumsDoubleBoard();
       vNum1.setValue(ex.getNum1());
       vNum2.setValue(ex.getNum2());
    }
    public boolean vCheck(String s){
        return ex.checkAnswer(s);
    }

}
