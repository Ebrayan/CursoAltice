package com.example.thebryan.personalsecurityapp.Util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.thebryan.personalsecurityapp.Models.News;
import com.example.thebryan.personalsecurityapp.Models.User;
import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.example.thebryan.personalsecurityapp.ActivityContact;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ConnectionToFireBase {
  public    static final int TYPE_USER=1;
    public static final int TYPE_CONTACT=2;
    public static final int TYPE_NEWS=3;
    public static final int TYPE_ROUTE=4;
    static  ConnectionToFireBase myConnectionToFireBase;
    FirebaseDatabase myDatabase;
    DatabaseReference reference;
    public String idContacto="Nada";

private ConnectionToFireBase(){
    myDatabase = FirebaseDatabase.getInstance();
    reference = myDatabase.getReference();
}






ArrayList<String>idUserContacts  = new ArrayList<>();
ArrayList<Contact>UserContacts = new ArrayList<>();

public Contact getSomeContact(String idContacto){
    final Contact[] con = new Contact[1];

    reference = myDatabase.getReference().child("Contacts").child(idContacto);
    reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                con[0] = dataSnapshot.getValue(Contact.class);
                con[0].setName(con[0].getName());
                con[0].setContact_Data(con[0].getContact_Data());
                Log.e("contactoS", con[0].getName());
                Log.e("contactoS", con[0].getContact_Data());
                UserContacts.add(new Contact(con[0].getName(),con[0].getContact_Data()));
                Log.e("contactoS", "Tamano de lista"+UserContacts.size());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    Log.e("contactoS", "Tamano de lista them"+UserContacts.size());
    return con[0];
}


public String setObjectOnFireDatabase(int objectType, Object object, String idElement){
    String uid = idElement;
    switch (objectType){

        case TYPE_USER :
            uid  = myDatabase.getReference().child("Usuarios").push().getKey();
            reference = myDatabase.getReference().child("Usuarios").child(uid);
            User user =   (User) object;
            user.setUserID(uid);
            reference.setValue(user);
            idContacto = uid;
            break;
        case  TYPE_CONTACT:
            try{

                uid  = myDatabase.getReference().child("Contacts").push().getKey();
                reference = myDatabase.getReference().child("Contacts").child(uid);
                Contact contact =   (Contact) object;
                contact.setIdUniqueCOntact(uid);
                reference.setValue(contact);
                idContacto = uid;
                break;
            }catch (DatabaseException e){


            }


        case TYPE_NEWS:
            uid  = myDatabase.getReference().child("News").push().getKey();
            reference = myDatabase.getReference().child("News").child(uid);
            News news=   (News) object;
            news.setDate(Calendar.getInstance().getTime().toString());
            reference.setValue(news);
            idContacto = uid;
            break;

        case TYPE_ROUTE:
            break;

    }


    return  uid;
}

public static ConnectionToFireBase getConnectionToFireBaseInstance (){

    if (myConnectionToFireBase == null){
        myConnectionToFireBase = new ConnectionToFireBase();
    }

        return myConnectionToFireBase;

}


public  ArrayList<String>getUserContact(){
    idUserContacts.clear();
    reference = myDatabase.getReference().child("Contacts");
    Query myQuery =  reference.orderByChild("trustedContacOf").equalTo(Aplication.getUserActives().getUserID());
    myQuery.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            ArrayList<Contact>cts = new ArrayList<>();
            for (DataSnapshot relations : dataSnapshot.getChildren()){
                Contact contact = relations.getValue(Contact.class);
                cts.add(contact);

            }
            Aplication.userActiveContacts =cts;
            ActivityContact.updateContactList(cts);
            Aplication.mySureContacts =cts.size();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    return  idUserContacts  ;
}
public  void  getUserContact(String l){
    idUserContacts.clear();
    reference = myDatabase.getReference().child("Contacts");
    Query myQuery =  reference.orderByChild("trustedContacOf").equalTo(Aplication.getUserActives().getUserID());
    myQuery.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            ArrayList<Contact>cts = new ArrayList<>();
            for (DataSnapshot relations : dataSnapshot.getChildren()){
                Contact contact = relations.getValue(Contact.class);
                cts.add(contact);

            }
            Aplication.userActiveContacts =cts;
            Aplication.mySureContacts =cts.size();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

}




public static  void  updateLocation(LatLng latLng){
    FirebaseDatabase  database  = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Usuarios").child(Aplication.getUserActives().getUserID()).child("Location");
    reference.setValue(latLng);

//    Query myQuery =  reference.orderByChild("trustedContacOf").equalTo(Aplication.getUserActives().getUserID());

}


}
