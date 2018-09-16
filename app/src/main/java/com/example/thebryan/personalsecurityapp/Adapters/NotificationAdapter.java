package com.example.thebryan.personalsecurityapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thebryan.personalsecurityapp.Models.Notification;
import com.example.thebryan.personalsecurityapp.Models.myLatLng;
import com.example.thebryan.personalsecurityapp.R;
import com.example.thebryan.personalsecurityapp.ActivityStaticMap;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolderContacts>
{


    ArrayList<Notification> my_notificaion_list;

public NotificationAdapter(ArrayList<Notification> list){
    this.my_notificaion_list = list;
}


    @Override
    public ViewHolderContacts onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notificacion, parent,false);

    return new ViewHolderContacts(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderContacts holder, final int position) {
                    final String userName= my_notificaion_list.get(position).getFrom_userName();
                    final myLatLng sendFrom= my_notificaion_list.get(position).getSendFrom();
                    final String message = my_notificaion_list.get(position).getMensaje();
                    final String date = my_notificaion_list.get(position).getDate();
                    //final LatLng latLng = new LatLng(sendFrom.getLatitude(),sendFrom.getLongitude());
                    final LatLng latLng = new LatLng(20,15);

                    holder.noti_date.setText(date);
                    holder.noti_message.setText(message);
                    holder.noti_From.setText(userName);
                    holder.noti_From.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Context context =  holder.noti_From.getContext();
                            Intent i =  new Intent(context,ActivityStaticMap.class );
                            i.putExtra("notificacionLocation", latLng);
                            context.startActivity(i);
                            //                            boolean isContactNumberOnDatabase  = isContactOnDatabase();
//                            if (isContactNumberOnDatabase){
//                                  ShowPhotoAnd_askToUser_ifisThis();
//                             }


                        }
                    });
    }

    @Override
    public int getItemCount() {
        return my_notificaion_list.size();
    }

    public class ViewHolderContacts extends RecyclerView.ViewHolder {

    TextView noti_date;
    TextView noti_message;
    TextView noti_From;

        public ViewHolderContacts(View itemView) {
            super(itemView);
            noti_From= itemView.findViewById(R.id.noti_From);
            noti_message= itemView.findViewById(R.id.noti_message);
            noti_date= itemView.findViewById(R.id.noti_date);

        }
    }
}
