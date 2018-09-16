package com.example.thebryan.personalsecurityapp.Models;

import com.google.android.gms.maps.model.LatLng;

public class Notification {


    public Notification() {

    }






    String from;
    String  to;
    String date;
    String mensaje;
    String From_userName;
    myLatLng sendFrom;






    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getFrom_userName() {
        return From_userName;
    }
    public void setFrom_userName(String from_userName) {
        From_userName = from_userName;
    }
    public myLatLng getSendFrom() {
        return sendFrom;
    }
    public void setSendFrom(myLatLng sendFrom) {
        this.sendFrom = sendFrom;
    }
}
