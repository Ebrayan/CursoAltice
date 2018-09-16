package com.example.thebryan.personalsecurityapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Toast;

import com.example.thebryan.personalsecurityapp.Adapters.PageAdapter;
import com.example.thebryan.personalsecurityapp.Util.Aplication;
import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;

import java.util.ArrayList;

public class ActivityMaps extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    private static MarkerOptions myMarker;
    public static LatLng myLatLng;
    static UltraViewPager ultraViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        updateUserListMap(this);
        UltraViewPager ultraViewPager = (UltraViewPager) findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        ultraViewPager.setMultiScreen(0.6f);
        ultraViewPager.setItemRatio(1.0f);
        ultraViewPager.setRatio(2.0f);
        ultraViewPager.setAutoMeasureHeight(true);


        ultraViewPager.setPageTransformer(false, new UltraDepthScaleTransformer());
        ultraViewPager.initIndicator();
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        ultraViewPager.getIndicator().build();

    }

    public  static void  updateUserListMap(final Activity activity){

                FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference;
                reference = myDatabase.getReference().child("Contacts");
                Query myQuery =  reference.orderByChild("contact_id_user").equalTo(Aplication.getUserActives().getUserID());
        final ArrayList<Contact> cts = new ArrayList<>();
                myQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot relations : dataSnapshot.getChildren()){
                            Contact contact = relations.getValue(Contact.class);
                            cts.add(contact);
                        }

                        updateMapUserList(cts,activity);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





    }

    public static  void  updateMapUserList(ArrayList<Contact> list, Activity activity){
        UltraViewPager ultraViewPager = (UltraViewPager) activity.findViewById(R.id.ultra_viewpager);
        Log.e("sizilist", ""+ list.size());
        PagerAdapter adapter1 = new PageAdapter(list);
        ultraViewPager.setAdapter(adapter1);

    }





    private LatLng getLocation() {

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getBaseContext(), "No hay acceso al GPS",
                    Toast.LENGTH_LONG)
                    .show();
            return new LatLng(5, -5);
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                myLatLng = new LatLng(location.getLatitude(), location.getLongitude());
//                changeLocation(myLatLng);
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
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return new LatLng(5, -5);
        }
        Location myLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        LatLng ubi=null;
if(myLocation==null){
    Toast.makeText(this,"Location is null",Toast.LENGTH_SHORT).show();
    ubi = new LatLng(18, -23);

}else{

    Double lat = myLocation.getLatitude();
    Double log = myLocation.getLongitude();
    if (lat== null ||log==null) {
        Toast.makeText(this,"lat o long is null",Toast.LENGTH_SHORT).show();
        ubi = new LatLng(18, -23);
    }else {
        ubi = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
    }

}



        return ubi;
    }

    public static void changeLocation(LatLng latLng) {
        if (latLng != null) {
            myMarker.position(latLng);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));

        }



    }


    public  void initMap (GoogleMap googleMap){

        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        myLatLng = getLocation();
        if (myLatLng != null){
            mMap.addMarker(myMarker = new MarkerOptions().position(myLatLng).title("Mi ubicacion").draggable(true)
                    .snippet("This is mySnippet")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_face_black_24dp)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
            try {
                // Customise the styling of the base map using a JSON object defined
                // in a raw resource file.
                boolean success = googleMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                this, R.raw.map_style2));

                if (!success) {
                    Log.e("mapa", "Style parsing failed.");
                }
            } catch (Resources.NotFoundException e) {
                Log.e("mimapa", "Can't find style. Error: ", e);
            }

        }else {
            Toast.makeText(getBaseContext(),"No se puede acceder a tu ubicacion", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),ActivityMain.class));

        }


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        initMap(googleMap);

    }



}
