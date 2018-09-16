package com.example.thebryan.personalsecurityapp;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.thebryan.personalsecurityapp.Adapters.ContactSwipeAdapter2;
import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.example.thebryan.personalsecurityapp.Util.ConnectionToFireBase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class ActivityContact extends AppCompatActivity  {
    static Context contact_activity_Context;
    public  static DialogPlus dialogPlus = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_activity);
        contact_activity_Context = this.getApplicationContext();
        final ViewHolder holder = new ViewHolder(R.layout.dialog);
         listView =  findViewById(R.id.myListMycontact);
        dialogPlus = DialogPlus.newDialog(ActivityContact.this)
                .setExpanded(true,ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeader(R.layout.dialog_header)
                .setMargin(5,5,5,0)
                .setContentHeight(600)
                .setContentHolder(holder)
                .create();
        findViewById(R.id.menu_item_seleccionarcContacto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                          dialogPlus.show();
            }
        });
        findViewById(R.id.menu_item_createContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent addnewContactIntent =  new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                        startActivity(addnewContactIntent);
                          dialogPlus.show();
            }
        });

        ConnectionToFireBase.getConnectionToFireBaseInstance().getUserContact();


    }

    public static Context getContact_activity_Context() {
        return contact_activity_Context;
    }

    static ListView listView;

    public static  void updateContactList(ArrayList <Contact> list){

    ArrayList myContacArrayList =list;
          ContactSwipeAdapter2 adapter2 = new ContactSwipeAdapter2(getContact_activity_Context(), myContacArrayList);
        listView.setAdapter(adapter2);
}

}
