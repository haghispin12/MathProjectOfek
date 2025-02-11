package com.example.mathprojectofek.mathProject;

import android.graphics.Bitmap;
import android.net.Uri;

public class User {
    long id;
    String name;
    int rate;
    int score;
    Bitmap bitmap;
    Uri uri;
    public User(long id, String name, int rating, Bitmap bitmap, int score) {
        this.id=id;
        this.name=name;
        this.rate=rating;
        this.bitmap=bitmap;
        this.score=score;
    }

    public User(String fruitName, int fruit) {
    }
    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
