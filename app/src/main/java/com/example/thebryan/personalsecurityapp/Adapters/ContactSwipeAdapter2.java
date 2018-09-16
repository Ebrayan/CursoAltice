package com.example.thebryan.personalsecurityapp.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.example.thebryan.personalsecurityapp.R;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ContactSwipeAdapter2 extends BaseSwipeAdapter {

        private Context mContext;
    ArrayList<Contact> contactArrayList;

        public ContactSwipeAdapter2(Context mContext, ArrayList<Contact> contactArrayList)  {
            this.mContext = mContext;
            this.contactArrayList = contactArrayList;
        }

        @Override
        public int getSwipeLayoutResourceId(int position) {
            return R.id.swipe;
        }


        @Override
        public View generateView(int position, ViewGroup parent) {
            return LayoutInflater.from(mContext).inflate(R.layout.conctact_item2, null);
        }

        @Override
        public void fillValues(int position, View convertView) {
            TextView txtpuedeverUbicacion = (TextView)convertView.findViewById(R.id.txtcanFallowMyLocation);
            TextView contact_parentescoMyListContact= (TextView)convertView.findViewById(R.id.contact_parentescoMyListContact);
            TextView contact_nameMyListContact= (TextView)convertView.findViewById(R.id.contact_nameMyListContact);
            txtpuedeverUbicacion.setText(valueOf(contactArrayList.get(position).getMapa()));
            contact_parentescoMyListContact.setText(contactArrayList.get(position).getContact_Data()) ;
            contact_nameMyListContact.setText(contactArrayList.get(position).getName()) ;
        }

        @Override
        public int getCount() {
            return contactArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

