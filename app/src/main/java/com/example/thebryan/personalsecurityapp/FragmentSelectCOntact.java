package com.example.thebryan.personalsecurityapp;

import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import com.example.thebryan.personalsecurityapp.Adapters.ContactAdapter;
import com.example.thebryan.personalsecurityapp.Util.Aplication;
import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.example.thebryan.personalsecurityapp.Util.ConnectionToFireBase;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FragmentSelectCOntact extends DialogFragment{

    public static ViewFlipper viewFlipper;
    Context contectActivityContect = ActivityContact.getContact_activity_Context();
    public static Button btn;
    static  ToggleButton toggleButton=null;
    static  ToggleButton toggleButton2=null;
    static  EditText txtParentesco = null;
    static  EditText txtIDContact= null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override


public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myFragmentDialog = inflater.inflate(R.layout.fragment_select_contact,container,false);

        viewFlipper= myFragmentDialog.findViewById(R.id.myViewFlipper);
        btn = myFragmentDialog.findViewById(R.id.btnSaveContactandUpdate);

        final RecyclerView myrecyclerViewContact = myFragmentDialog.findViewById(R.id.myrecyclercontact);
        toggleButton= myFragmentDialog.findViewById(R.id.toggleButton);
        toggleButton2= myFragmentDialog.findViewById(R.id.toggleButtoniSUser);
        txtParentesco= myFragmentDialog.findViewById(R.id.txtParentesco);
        txtIDContact= myFragmentDialog.findViewById(R.id.txtID);
        txtIDContact.setEnabled(false);

        toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked){
                            txtIDContact.setEnabled(true);
                        }else{
                            txtIDContact.setEnabled(false);
                        }
            }
        });


        myrecyclerViewContact.setLayoutManager(new LinearLayoutManager(ActivityContact.getContact_activity_Context()));
        try {
            myrecyclerViewContact.setAdapter(new Load_inbacground_contact()
                    .execute("")
                    .get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return myFragmentDialog;

    }


    static  String newContactName;
    static String newContactData;





    public static void   sendNewContactData(String name, String data){
     newContactName =name;
     newContactData =data;

    }
public static void createContact(){



    final String parentesco = txtParentesco.getText().toString();
    final String  idOfUser= txtIDContact.getText().toString();
    String  mapa= "";
    if(toggleButton.isChecked()){
        mapa="puede ver mi Ubicaicon";
    }else{
        mapa="no puede ver mi Ubicaicon";
    }

    ActivityContact.dialogPlus.dismiss();
        if(!toggleButton2.isChecked()){
            Contact contact = new Contact(newContactName,newContactData, Aplication.getUserActives().getUserID(), parentesco,mapa);
            ConnectionToFireBase.getConnectionToFireBaseInstance()
                    .setObjectOnFireDatabase(ConnectionToFireBase.TYPE_CONTACT,contact, ConnectionToFireBase.getConnectionToFireBaseInstance().idContacto);
         //   ConnectionToFireBase.getConnectionToFireBaseInstance().getUserContact();
        }else {
            Contact contact2 = new Contact(newContactName,newContactData, Aplication.getUserActives().getUserID(), parentesco,mapa,idOfUser);
            ConnectionToFireBase.getConnectionToFireBaseInstance()
                    .setObjectOnFireDatabase(ConnectionToFireBase.TYPE_CONTACT,contact2, ConnectionToFireBase.getConnectionToFireBaseInstance().idContacto);
           // ConnectionToFireBase.getConnectionToFireBaseInstance().getUserContact();
        }
    FragmentSelectCOntact.viewFlipper.showPrevious();


}
    private class Load_inbacground_contact extends AsyncTask<String , String, ContactAdapter> {


        @Override
        protected ContactAdapter doInBackground(String... strings) {

            ArrayList<Contact> contacts = loadallcontact();

            ContactAdapter adaptercontact = new ContactAdapter(contacts);
            return adaptercontact;
        }



        private ArrayList<Contact> loadallcontact() {
            ArrayList<Contact> mycontactsArrayList  = new ArrayList<>();
            ContentResolver mycontact_content = contectActivityContect.getContentResolver();
            Cursor my_cursor=  mycontact_content.query(ContactsContract.Contacts.CONTENT_URI, null,null,null,null);
            if (my_cursor.getCount() > 0) {

                while (my_cursor.moveToNext()){

                    String id = my_cursor.getString(my_cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = my_cursor.getString(my_cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    int hasnumber= Integer.parseInt(my_cursor.getString(my_cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                    if (hasnumber >0){
                        Cursor myCursor2 = mycontact_content.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",
                                new String[]{id},null);


                        while (myCursor2.moveToNext()){

                            String number =  myCursor2.getString(myCursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            Contact mycontact = new Contact(name,number);
                            mycontactsArrayList.add(mycontact);


                        }
                        myCursor2.close();
                    }
                }
            }
            my_cursor.close();
            return mycontactsArrayList;
        }

    }
}