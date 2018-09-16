package com.example.thebryan.personalsecurityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.thebryan.personalsecurityapp.Util.Aplication;
import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.example.thebryan.personalsecurityapp.Models.Notification;
import com.example.thebryan.personalsecurityapp.Models.myLatLng;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivitySomeEmergency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_emergency);

        LinearLayout btnEmergecySick= findViewById(R.id.btnEmergecySick);
        LinearLayout btnViolencia= findViewById(R.id.violencia);
        btnEmergecySick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendNotificacion("Un usuario tiene problema");
            }
        });

        btnViolencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivitySomeEmergency.this,"btn No coded", Toast.LENGTH_LONG).show();
            }
        });




    }


    private void sendNotificacion(String mensaje) {

        ArrayList<Contact> contacts = Aplication.userActiveContacts;
        for (Contact cts : contacts){
            if(cts.getContact_id_user()!=null){

                LatLng latLng = Aplication.getActuallyLocation();
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                        .child("Usuarios").child(cts.getContact_id_user()).child("Notification");
                String idNoti  = reference.push().getKey();
                Notification notification = new Notification();
                notification.setFrom(Aplication.getUserActives().getUserID());
                notification.setFrom_userName(Aplication.getUserActives().getUsername());
                notification.setMensaje(mensaje);
                if(latLng == null){
                    notification.setSendFrom(new myLatLng(0,0));
                }else{

                    notification.setSendFrom(new myLatLng(latLng.latitude,latLng.longitude));
                }
                notification.setTo(cts.getContact_id_user());
                notification.setDate(Calendar.getInstance().getTime().toString());
                reference.child(idNoti).setValue(notification);
            }

        }
    }
}
