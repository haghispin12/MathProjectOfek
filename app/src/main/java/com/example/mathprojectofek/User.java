package com.example.mathprojectofek;

import android.graphics.Bitmap;
import android.net.Uri;

public class User {
    long id;
    String name;
    int rate;
    int score;

    Uri uri;
    public User(long id, String name, int rating, Bitmap bitmap, int score) {
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
}
