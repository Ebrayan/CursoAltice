package com.example.thebryan.personalsecurityapp.Util;

import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.example.thebryan.personalsecurityapp.Models.News;
import com.example.thebryan.personalsecurityapp.Models.Notification;
import com.example.thebryan.personalsecurityapp.Models.User;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Aplication {

    public  Aplication (){

    }



    public static void   init (User userActive){
        LoginSomeUser(userActive);
            new InitListeners();
    }











    static User userActives ;
    public static ArrayList<Notification> notifications;
    public static ArrayList<News> news;
    public static ArrayList<Contact> userActiveContacts;
    public static int Quantity_peopleWhouserActiveIsSureContac;
    public static int mySureContacts;
    static LatLng actuallyLocation;
    String userLat;
    String userLng;






    public static ArrayList<Contact> getUserActiveContacts() {
        return userActiveContacts;
    }
    public static int getQuantity_peopleWhouserActiveIsSureContac() {
        return Quantity_peopleWhouserActiveIsSureContac;
    }
    public static void setQuantity_peopleWhouserActiveIsSureContac(int quantity_peopleWhouserActiveIsSureContac) {
        Aplication.Quantity_peopleWhouserActiveIsSureContac = quantity_peopleWhouserActiveIsSureContac;
    }
    public static void setUserActiveContacts(ArrayList<Contact> userActiveContacts) {
        Aplication.userActiveContacts = userActiveContacts;
    }
    public static int getMySureContacts() {
        return mySureContacts;
    }
    public static User getUserActives() {
        return userActives;
    }
    public static void setMySureContacts(int mySureContacts) {
        Aplication.mySureContacts = mySureContacts;
    }
    public static void setUserActives(User userActives) {
        Aplication.userActives = userActives;
    }
    public static LatLng getActuallyLocation() {
        return actuallyLocation;
    }
    public static  void LoginSomeUser(User userActive){
        userActives  =  userActive;
    }
    public static void setActuallyLocation(LatLng actuallyLocation) {
        Aplication.actuallyLocation = actuallyLocation;
    }
    public String getUserLng() {
        return userLng;
    }
    public void setUserLng(String userLng) {
        this.userLng = userLng;
    }
    public String getUserLat() {
        return userLat;
    }

    public void setUserLat(String userLat) {
        this.userLat = userLat; }





}
