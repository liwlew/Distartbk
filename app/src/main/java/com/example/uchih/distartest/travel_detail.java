package com.example.uchih.distartest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class travel_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);

        clickbtm();
    }

    private void clickbtm(){
        ImageButton next1 = (ImageButton)findViewById(R.id.btmback);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(travel_detail.this,travel.class);
                startActivity(i);
            }
        });
    }
}
