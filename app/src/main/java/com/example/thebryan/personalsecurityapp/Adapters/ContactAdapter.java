package com.example.thebryan.personalsecurityapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thebryan.personalsecurityapp.Models.Contact;
import com.example.thebryan.personalsecurityapp.R;
import com.example.thebryan.personalsecurityapp.FragmentSelectCOntact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolderContacts>
{


    ArrayList<Contact> my_contact_list;

public ContactAdapter(ArrayList<Contact> list){
    my_contact_list = list;
}


    @Override
    public ViewHolderContacts onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_contact, parent,false);

    return new ViewHolderContacts(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderContacts holder, final int position) {
                    final String contact_data = my_contact_list.get(position).getContact_Data();
                    final String contact_Name = my_contact_list.get(position).getName();
                    holder.txtName.setText(contact_Name);
                    holder.txtData.setText(contact_data );
                    holder.txtName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FragmentSelectCOntact.viewFlipper.showNext();
                            FragmentSelectCOntact.sendNewContactData(contact_Name,contact_data);
                            FragmentSelectCOntact.btn = FragmentSelectCOntact.viewFlipper.findViewById(R.id.btnSaveContactandUpdate);
                            FragmentSelectCOntact.btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    FragmentSelectCOntact.createContact();


                                }
                            });
//                            boolean isContactNumberOnDatabase  = isContactOnDatabase();
//                            if (isContactNumberOnDatabase){
//                                  ShowPhotoAnd_askToUser_ifisThis();
//                             }


                        }
                    });
    }

    @Override
    public int getItemCount() {

        return my_contact_list.size();
    }

    public class ViewHolderContacts extends RecyclerView.ViewHolder {

    TextView txtName;
    TextView txtData;
    public ViewHolderContacts(View itemView) {
            super(itemView);
            txtData = itemView.findViewById(R.id.contact_parentesco);
            txtName= itemView.findViewById(R.id.contact_name);

        }
    }
}
