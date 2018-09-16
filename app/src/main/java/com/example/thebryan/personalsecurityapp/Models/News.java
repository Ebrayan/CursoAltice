package com.example.thebryan.personalsecurityapp.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class News {
    String userName;

    String image;
    String  tittle;
    String content;
    String date;
    String user;
    ArrayList <Comments>  newsComents  =  new ArrayList<>();







    public String getImage() {
        return image;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getTittle() {
        return tittle;
    }
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public ArrayList<Comments> getNewsComents() {
        return newsComents;
    }
    public void setNewsComents(ArrayList<Comments> newsComents) {
        this.newsComents = newsComents;
    }



}
