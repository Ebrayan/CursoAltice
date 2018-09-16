package com.example.thebryan.personalsecurityapp.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.example.thebryan.personalsecurityapp.R;
import com.example.thebryan.personalsecurityapp.ActivityContact;

import java.util.ArrayList;

public class Adatapters extends BaseAdapter {


    ArrayList<Contact> contactArrayList;


    public  Adatapters (ArrayList<Contact> contactArrayList){
        this.contactArrayList = contactArrayList;
    }

    @Override
    public int getCount() {
        return contactArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view =  View.inflate(ActivityContact.getContact_activity_Context(), R.layout.item_list_contact,viewGroup);
        view.findViewById(R.id.contact_parentesco);
        view.findViewById(R.id.contact_name);
        return view;

    }
}
