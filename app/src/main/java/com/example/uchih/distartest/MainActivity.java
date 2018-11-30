package com.example.uchih.distartest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;


import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText addBox;
    private Button addButt;
    private MySQLConnect mySQLCon;
    private ListView dataListView;
    private List<String> items;
    private List<String> items2;
    private ArrayAdapter<String> adt; ///เพิ่มเข้ามาใหม่ล่าสุด
private TextView textView ;
    public void update(){
        //item ตัวที่ส่ง
        items = mySQLCon.getData();
       // items2 = mySQLCon.getData2();
       // adt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items); //เพิ่มเข้ามาใหม่ล่าสุด
       // dataListView.setAdapter(adt); //ทดแทนกันโดยเรียกใช้ adt แทน

    /*   Log.d("liwlew","testbyliw----"+items);*/
       // textView.setText(items);

    }

    public void init(){
     //   addBox = (EditText)findViewById(R.id.addBox);
      //  addButt = (Button)findViewById(R.id.addButt);
   //     dataListView = (ListView)findViewById(R.id.dataView);

        mySQLCon = new MySQLConnect(MainActivity.this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Pordee Expoess");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor((Color.parseColor("#4CAF50")));
//        toolbar.setSubtitle("test2");
//        toolbar.setSubtitleTextColor(Color.RED);
        setSupportActionBar(toolbar);
        init();
        update();
        //สร้างปุ่ม



        itemclick();
        Button next2 = (Button)findViewById(R.id.button2);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,travel.class);
                startActivity(i);
            }

        });
        Button next3 = (Button)findViewById(R.id.button3);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,food.class);
                startActivity(i);
            }
        });




        Button next4 = (Button)findViewById(R.id.button4);
        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,hotel.class);
                startActivity(i);
            }
        });

        Button testl = (Button)findViewById(R.id.testlog);
        testl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("liwlew","testbyliw----"+items);
            }
        });


// image load url
        DownloadImageTask task = (DownloadImageTask) new DownloadImageTask((ImageView) findViewById(R.id.image_item1))
                .execute("http://pordeeshops.com/image/cache/catalog/product1/forman/Image_6a3d794-1600x1600-200x200.jpg");

        DownloadImageTask task2 = (DownloadImageTask) new DownloadImageTask((ImageView) findViewById(R.id.image_item2))
                .execute("http://pordeeshops.com/image/cache/catalog/product1/forman/Image_b3f4973-1600x1600-200x200.jpg");
        DownloadImageTask task3 = (DownloadImageTask) new DownloadImageTask((ImageView) findViewById(R.id.image_item3))
                .execute("http://pordeeshops.com/image/cache/catalog/product1/forman/Image_7f3d196-1600x1600-200x200.jpg");
        DownloadImageTask task4 = (DownloadImageTask) new DownloadImageTask((ImageView) findViewById(R.id.image_item4))
                .execute("http://pordeeshops.com/image/cache/catalog/product1/forman/Image_a71ecfc-1600x1600-200x200.jpg");
         if(items.size()!=0){
           imbtm();
            }

    }



    private  void imbtm(){

        for(int i =0;i<4;i++){
            LinearLayout buttonContainer = (LinearLayout) findViewById(R.id.container_btm);
            ImageButton button = new ImageButton(this);
            DownloadImageTask task = (DownloadImageTask) new DownloadImageTask(button)
                    .execute("http://pordeeshops.com/image/cache/"+items.get(1));
            //Button button = new Button(this);

            buttonContainer.addView(button);



        }
    }

private  void itemclick (){
    ImageButton next9 = (ImageButton)findViewById(R.id.image_item1);
    next9.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(MainActivity.this,item.class);
            startActivity(i);
        }
    });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id==R.id.action_search){
//            return  true;
//        }
//        return  super.onOptionItemSelected(item);
//    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
