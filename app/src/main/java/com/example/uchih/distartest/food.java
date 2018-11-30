package com.example.uchih.distartest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class food extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Pordee Expoess");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor((Color.parseColor("#4CAF50")));
        setSupportActionBar(toolbar);

        Button next2 = (Button)findViewById(R.id.button);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(food.this,MainActivity.class);
                startActivity(i);
            }
        });
        Button next3 = (Button)findViewById(R.id.button2);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(food.this,travel.class);
                startActivity(i);
            }
        });
        Button next4 = (Button)findViewById(R.id.button4);
        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(food.this,hotel.class);
                startActivity(i);
            }
        });

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://www.google.co.th/maps/place/%E0%B9%84%E0%B8%8A%E0%B8%99%E0%B9%88%E0%B8%B2%E0%B9%80%E0%B8%A7%E0%B8%B4%E0%B8%A5%E0%B8%94%E0%B9%8C/@13.744892,100.4997003,17z/data=!3m1!4b1!4m5!3m4!1s0x30e299199bd7b5cb:0xf6e4dc60a108c4bc!8m2!3d13.7448868!4d100.501889?hl=th&authuser=0");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        ImageButton btn2 = (ImageButton) findViewById(R.id.button5);
        ImageButton btn = (ImageButton) findViewById(R.id.button6);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebView myWebView = (WebView) findViewById(R.id.webview);
                myWebView.loadUrl("https://www.google.co.th/maps/place/Sanam+Luang/@13.7551788,100.4908758,17z/data=!3m1!4b1!4m5!3m4!1s0x30e2990db4a963f9:0x69fc3357641e5a44!8m2!3d13.7551736!4d100.4930645?hl=th&authuser=0");
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WebView myWebView = (WebView) findViewById(R.id.webview);
                myWebView.loadUrl("https://www.google.co.th/maps/place/%E0%B8%AA%E0%B8%A7%E0%B8%99%E0%B8%A5%E0%B8%B8%E0%B8%A1%E0%B8%9E%E0%B8%B4%E0%B8%99%E0%B8%B5/@13.7307211,100.5396244,16.67z/data=!4m5!3m4!1s0x30e29f26ed4bb01b:0xac01b20801f96936!8m2!3d13.7314058!4d100.5414396?hl=th&authuser=0");
                WebSettings webSettings = myWebView.getSettings();
                webSettings.setJavaScriptEnabled(true);
            }
        });


    }
}
