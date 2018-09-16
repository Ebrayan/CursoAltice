package com.example.thebryan.personalsecurityapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.thebryan.personalsecurityapp.Util.Aplication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyServices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    public void  IamNoSure()
    {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(Aplication.getUserActives().getUserID()).child("Security");
        reference.setValue("no");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 String security = dataSnapshot.getValue(String.class);
                 if (security.equals("no")){

                    // sendNotificacion();

                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}
