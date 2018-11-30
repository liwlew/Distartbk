package com.example.uchih.distartest;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class testmap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testmap);

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("test");
        toolbar.setTitleTextColor(Color.CYAN);
        toolbar.setSubtitle("test2");
        toolbar.setSubtitleTextColor(Color.RED);
       //setSupportActionBar(toolbar);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(13.812528, 100.520800);
        mMap.addMarker(new MarkerOptions().position(sydney).title("วัดพระแก้ว"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
        ImageButton btn1 = (ImageButton) findViewById(R.id.imageButton2);
        // Perform action on click
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mMap.clear();
                LatLng sydney = new LatLng(13.7516435, 100.4927041);
                mMap.addMarker(new MarkerOptions().position(sydney).title("วัดพระแก้ว"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
            }
        });
        Button btn2 = (Button) findViewById(R.id.buttonNormal);
        // Perform action on click
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mMap.clear();
                LatLng sydney = new LatLng(13.9092498, 100.4746934);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Home"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
            }
        });
    }
}
