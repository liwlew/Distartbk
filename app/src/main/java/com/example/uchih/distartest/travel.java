package com.example.uchih.distartest;


import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class travel extends AppCompatActivity {
    private GoogleMap mMap;
    private MapFragment mMapFragment;
    private  LatLng sydney = new LatLng(-34, 151);
    private LatLng myHome = new LatLng(13.812528, 100.520800);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Pordee Expoess");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor((Color.parseColor("#4CAF50")));
        setSupportActionBar(toolbar);


        Button next2 = (Button)findViewById(R.id.button);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(travel.this,MainActivity.class);
                startActivity(i);
            }
        });
        Button next3 = (Button)findViewById(R.id.button3);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(travel.this,food.class);
                startActivity(i);
            }
        });
        Button next4 = (Button)findViewById(R.id.button4);
        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(travel.this,hotel.class);
                startActivity(i);
            }
        });

        Button google = (Button)findViewById(R.id.button6);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleMap(myHome, sydney);
            }
        });



        FragmentManager fragMen = getFragmentManager();
        mMapFragment =(MapFragment)fragMen.findFragmentById(R.id.map_frag);
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                // Add a marker in Sydney and move the camera
                 sydney = new LatLng(13.812528, 100.520800);
                mMap.addMarker(new MarkerOptions().position(sydney).title("วัดพระแก้ว"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                ImageButton btn1 = (ImageButton) findViewById(R.id.imageButton2);
                // Perform action on click
                btn1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        mMap.clear();
                         sydney = new LatLng(13.7516435, 100.4927041);
                        mMap.addMarker(new MarkerOptions().position(sydney).title("วัดพระแก้ว"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                    }
                });
                Button btn2 = (Button) findViewById(R.id.buttonNormal);
                // Perform action on click
                btn2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        mMap.clear();
                         sydney = new LatLng(13.9092498, 100.4746934);
                        mMap.addMarker(new MarkerOptions().position(sydney).title("Home"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                    }
                });
            }
        });

    }

    private void openGoogleMap(LatLng src, LatLng dest) {
        String url = "http://maps.google.com/maps?saddr="+src.latitude+","+src.longitude+"&daddr="+dest.latitude+","+dest.longitude+"&mode=driving";
        Uri gmmIntentUri = Uri.parse(url);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
