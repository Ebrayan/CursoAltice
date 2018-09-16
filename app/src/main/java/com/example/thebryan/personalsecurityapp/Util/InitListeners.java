package com.example.thebryan.personalsecurityapp.Util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.ImageView;

import com.example.thebryan.personalsecurityapp.ActivityIntro;
import com.example.thebryan.personalsecurityapp.ActivityLogin;
import com.example.thebryan.personalsecurityapp.ActivityMain;
import com.example.thebryan.personalsecurityapp.ActivityViewNews;
import com.example.thebryan.personalsecurityapp.FragmetNotification;
import com.example.thebryan.personalsecurityapp.Models.News;
import com.example.thebryan.personalsecurityapp.Models.Notification;
import com.example.thebryan.personalsecurityapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InitListeners {


    static  boolean isfirstIn;

    public InitListeners(){
        //getting all news
        //getNewsList();
        initNotifications();
    }







    public static void showNotification() {
        Context cont = ActivityIntro.getActivityIntroContext();
        long [] pattern = {500,500,500,500,500,500,500,500,500,500};
        NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(cont);
        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(cont)
                .setSmallIcon(R.drawable.ic_report_problem_black_24dp)
                .setContentTitle("Alguien necesita de tu ayuda")
                .setContentText("")
                .setVibrate(pattern)
                .setLights(Color.RED,500,500)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        Intent targetIntent = new Intent(cont, ActivityMain.class);
        PendingIntent contentIntent = PendingIntent.getActivity(cont, 0, targetIntent,  PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(1, mBuilder.build());

    }

    public static  void  initNotifications(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                .child("Usuarios").child(Aplication.getUserActives().getUserID()).child("Notification");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Notification> notifications = new ArrayList<>();
                for (DataSnapshot notification : dataSnapshot.getChildren()){
                    com.example.thebryan.personalsecurityapp.Models.Notification noti= notification.getValue(com.example.thebryan.personalsecurityapp.Models.Notification.class);
                    notifications.add(noti);
                }

                FragmetNotification.fillNotyfyList(notifications);
                if(!isfirstIn){
                    showNotification();
                    Aplication.notifications = notifications;
                }
                isfirstIn =false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static  void  getNewsList(){
        FirebaseDatabase myDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference reference;
        reference = myDatabase.getReference().child("News");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<News>news= new ArrayList<>();
                for (DataSnapshot relations : dataSnapshot.getChildren()){
                    News news1= relations.getValue(News.class);
                    news.add(news1);
                }
                ActivityViewNews.fillNewList(news);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }



}
