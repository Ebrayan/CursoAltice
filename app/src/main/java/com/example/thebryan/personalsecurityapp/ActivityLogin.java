package com.example.thebryan.personalsecurityapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thebryan.personalsecurityapp.Util.Aplication;
import com.example.thebryan.personalsecurityapp.Models.User;
import com.example.thebryan.personalsecurityapp.Util.ConnectionToFireBase;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ActivityLogin extends AppCompatActivity {

    private static Context activityLoginContext;

    public static Context getActivityLoginContext() {
        return activityLoginContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         activityLoginContext = this.getBaseContext();
        Button btnsign_in =  findViewById(R.id.btnsign_in);
        btnsign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtUser;
                final EditText txtpass;
                txtUser = findViewById(R.id.txtuser);
                txtpass= findViewById(R.id.txtpass);
                DatabaseReference reference=FirebaseDatabase.getInstance()
                        .getReference();
                Query query = reference.child("Usuarios").orderByChild("username").equalTo(txtUser.getText().toString().trim());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                User usersBean = user.getValue(User.class);
                                if (usersBean.getPass().equals(txtpass.getText().toString().trim())) {
                                    Aplication.init(usersBean);
                                    Intent intent = new Intent(ActivityLogin.this, ActivityMain.class);
                                     startActivity(intent);
                                    ubicacion();
                                } else {
                                    Toast.makeText(ActivityLogin.this, "Contrase;a incorrecta", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(ActivityLogin.this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//                String user = txtUser.getText().toString();
//                final String pass= txtpass.getText().toString();
//                DatabaseReference reference=FirebaseDatabase.getInstance()
//                        .getReference().
//                                child("Usuarios");
//                Query query =  reference.orderByChild("username").equalTo(user);
//                query.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for (DataSnapshot relations : dataSnapshot.getChildren()){
//                            User user= relations.getValue(User.class);
//                                if(pass.equals(user.getPass())){
//                                    Aplication.LoginSomeUser(user);
//                                    break;
//
//                                }else {
//                                    Toast.makeText(ActivityLogin.this,"Pass inconrrecta",Toast.LENGTH_SHORT).show();
//                                }
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(ActivityLogin.this,"Error al iniciar sesion",Toast.LENGTH_SHORT).show();
//                    }
//
//                });


            }
        });


    }


    public  void  ubicacion(){
        LocationManager mlocManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        LocationListener mlocListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Aplication.setActuallyLocation(new LatLng(location.getLatitude(),location.getLongitude()));
                ConnectionToFireBase.updateLocation(new LatLng(location.getLatitude(),location.getLongitude()));

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);


    }
}
