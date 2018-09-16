package com.example.thebryan.personalsecurityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

public class ActivityStaticMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_map);
        LatLng latLng = null;
        Bundle b = getIntent().getExtras();
            if (b!=null){
                 latLng = (LatLng) b.get("notificacionLocation");
            }

        ImageView imageView = findViewById(R.id.imageMap);
        Picasso.get().load("http://maps.google.com/maps/api/staticmap?center=" + latLng.latitude + "," +latLng.longitude + "&zoom=8&size=350x350&sensor=false").into(imageView);


    }
}
