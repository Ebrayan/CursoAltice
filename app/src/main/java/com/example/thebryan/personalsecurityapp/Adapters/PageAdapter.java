package com.example.thebryan.personalsecurityapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thebryan.personalsecurityapp.ActivityMaps;
import com.example.thebryan.personalsecurityapp.Models.User;
import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.example.thebryan.personalsecurityapp.Models.myLatLng;
import com.example.thebryan.personalsecurityapp.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PageAdapter extends PagerAdapter {


    ArrayList<Contact> arrayListWhoIamTrusted;
    public PageAdapter(ArrayList<Contact> arrayListWhoIamTrusted){
        this.arrayListWhoIamTrusted =arrayListWhoIamTrusted;
    }

    @Override
    public int getCount() {
        return arrayListWhoIamTrusted.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o==view;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {


        final View params = LayoutInflater.from(container.getContext()).inflate(R.layout.item_somecontact, null,false);
        final int page = position;
        final String idTOFallow =arrayListWhoIamTrusted.get(position).getTrustedContacOf();
        final TextView userNameToFallow = params.findViewById(R.id.userNameToFallow);
        final TextView NumberToFallow = params.findViewById(R.id.numbberToFallow);
        final TextView IDToFallow= params.findViewById(R.id.IDToFallow);
        final ImageView imageView= params.findViewById(R.id.contact_imagemap);
        FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference;
        reference = myDatabase.getReference().child("Usuarios").child(idTOFallow);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                user.setUserID(idTOFallow);
                if(user.getProfileImageURL()!=null){
                    Picasso.get().load(user.getProfileImageURL()).into(imageView);
                }
                NumberToFallow.setText(user.getMobileNumber());
                userNameToFallow.setText(user.getUsername());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        params.findViewById(R.id.contact_imagemap).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LatLng ubi = ActivityMaps.myLatLng;
                ActivityMaps.changeLocation(ubi);
                FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference;
                reference = myDatabase.getReference().child("Usuarios").child(IDToFallow.getText().toString()).child("Location");
                reference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        myLatLng location  = dataSnapshot.getValue(myLatLng.class);
                        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                        ActivityMaps.changeLocation(latLng);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(container.getContext(), "You clicked: " + page + ". page.", Toast.LENGTH_SHORT).show();


            }
        });

        container.addView(params);
        return params;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
