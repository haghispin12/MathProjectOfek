package com.example.mathprojectofek;
import android.widget.EditText;

import java.util.Random;

public class Exercise {
    public int num1;
    public int num2;

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }
    public void setNum2(int num2) {
        this.num2 = num2;
    }
    public void  generateNumsChallenge(){
        Random r=new Random();
        num1=r.nextInt(10)+1;
        num2=r.nextInt(90)+10;
    }
    public void generateNumsTill20(){
        Random r=new Random();
        num1=r.nextInt(10)+1;
        num2=r.nextInt(10)+10;
    }
    public void  generateNumsDoubleBoard(){
        Random r=new Random();
        num1=r.nextInt(10)+1;
        num2=r.nextInt(10)+1;
    }
    public boolean checkAnswer(EditText answer){
        //String Num1;
        String result =num1*num2+"";
        if (result.equals(answer.getText().toString()))
            return true;
        else
            return false;
    }

}
