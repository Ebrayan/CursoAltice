package com.example.thebryan.personalsecurityapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import com.example.thebryan.personalsecurityapp.Adapters.NewsAdapter;
import com.example.thebryan.personalsecurityapp.Models.News;
import com.example.thebryan.personalsecurityapp.Util.Aplication;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraVerticalTransformer;

import java.util.ArrayList;

public class ActivityViewNews extends AppCompatActivity {
    static  UltraViewPager ultraViewPagerNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);

        ultraViewPagerNews = (UltraViewPager) findViewById(R.id.ultra_viewpager_new);
        ultraViewPagerNews.initIndicator();
        ultraViewPagerNews.setScrollMode(UltraViewPager.ScrollMode.VERTICAL);
        ultraViewPagerNews.setItemRatio(1.0f);
        ultraViewPagerNews.setRatio(2.0f);
        ultraViewPagerNews.setPageTransformer(false, new UltraVerticalTransformer());
        ultraViewPagerNews.initIndicator();
        ultraViewPagerNews.getIndicator()
                .setOrientation(UltraViewPager.Orientation.VERTICAL)
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE);
        ultraViewPagerNews.getIndicator().setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM);
        ultraViewPagerNews.getIndicator().build();

    }

    public  static  void  fillNewList(ArrayList<News> list){
        NewsAdapter adapter = new NewsAdapter(list);
        ultraViewPagerNews.setAdapter(adapter);

    }

}
