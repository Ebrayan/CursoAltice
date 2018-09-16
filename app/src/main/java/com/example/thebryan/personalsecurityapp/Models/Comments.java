package com.example.thebryan.personalsecurityapp.Models;


class Comments {
    String idComent;
    User user;
    String text;
    String fecha;

    public String getIdComent() {
        return idComent;
    }

    public void setIdComent(String idComent) {
        this.idComent = idComent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Comments() {

    }
}
