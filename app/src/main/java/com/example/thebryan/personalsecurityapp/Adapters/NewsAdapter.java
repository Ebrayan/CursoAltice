package com.example.thebryan.personalsecurityapp.Adapters;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thebryan.personalsecurityapp.Models.News;
import com.example.thebryan.personalsecurityapp.Models.User;
import com.example.thebryan.personalsecurityapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewsAdapter extends PagerAdapter {


    ArrayList<News> arrayListNews;
    public NewsAdapter(ArrayList<News> arrayListNews){
        this.arrayListNews = arrayListNews;
    }

    @Override
    public int getCount() {
        return arrayListNews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o==view;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        final View params = LayoutInflater.from(container.getContext()).inflate(R.layout.item_news, null,false);

        TextView news_title = params.findViewById(R.id.news_title);
        TextView news_Content = params.findViewById(R.id.news_content);
        TextView news_date = params.findViewById(R.id.newdate);
        TextView news_user = params.findViewById(R.id.userpublishnews);



        ImageView news_image = params.findViewById(R.id.news_image);

        news_title.setText(arrayListNews.get(position).getTittle());
        news_Content.setText(arrayListNews.get(position).getContent());
        news_date.setText(arrayListNews.get(position).getDate());
        news_user.setText(arrayListNews.get(position).getUserName());
         Picasso.get().load(arrayListNews.get(position).getImage()).into(news_image);
        container.addView(params);
        return params;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
