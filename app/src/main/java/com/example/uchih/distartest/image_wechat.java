package com.example.uchih.distartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class image_wechat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_wechat_layout);


        ImageView image = (ImageView)findViewById(R.id.image_wechat);
        Picasso.get().load(datamysql.image_wechat).into(image);
    }
}
