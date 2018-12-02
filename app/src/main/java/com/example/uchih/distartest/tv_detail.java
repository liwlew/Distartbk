package com.example.uchih.distartest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class tv_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        clickbtm();
    }
    private void clickbtm(){
        ImageButton next1 = (ImageButton)findViewById(R.id.btmback1);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tv_detail.this,travel.class);
                startActivity(i);
            }
        });
    }
}
