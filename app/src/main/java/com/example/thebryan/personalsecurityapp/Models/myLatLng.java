package com.example.thebryan.personalsecurityapp.Models;

import com.google.android.gms.maps.model.LatLng;

public class myLatLng {

    public myLatLng(){}
    public myLatLng(double latitude, double longitude) {
        this.latitude =latitude;
        this.longitude=longitude;

    }





    double longitude;
    double latitude;








    public LatLng getLatLng (){
        LatLng latLng = new LatLng(getLatitude(),getLongitude());
        return latLng;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }



}
