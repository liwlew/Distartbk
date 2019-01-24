package com.example.uchih.distartest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;


import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText addBox;
    private Button addButt;
    private MySQLConnect mySQLCon;
    private GridView dataListView;
    private List<String> items;
    private List<String> items2;
    private ArrayAdapter<String> adt; ///เพิ่มเข้ามาใหม่ล่าสุด
    private GridView griview;
    private TextView textView ;

    private  int sizesql ;
    private   String[]image;
    private   String[]price;
    private   String[] name ;

    public void update(){
        //item ตัวที่ส่ง
       //  items = mySQLCon.getData();
       // items2 = mySQLCon.getData2();
          adt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items); //เพิ่มเข้ามาใหม่ล่าสุด
        //   dataListView.setAdapter(adt); //ทดแทนกันโดยเรียกใช้ adt แทน

        /*   Log.d("liwlew","testbyliw----"+items);*/
        // textView.setText(items);

    }

    public void init(){
        //   addBox = (EditText)findViewById(R.id.addBox);
        //  addButt = (Button)findViewById(R.id.addButt);
        //dataListView = (GridView)findViewById(R.id.dataView);

     //   mySQLCon = new MySQLConnect(MainActivity.this);
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
       // init();
    //  update();
        //สร้างปุ่ม
        griview = (GridView)findViewById(R.id.gridview1);
        griview.setAdapter(new Efficientadapter(getApplicationContext()));

        griview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                datamysql.productid = String.valueOf(position);
                Intent i = new Intent(MainActivity.this,item.class);
                startActivity(i);

            }
        });

        griview.performClick();


        //itemclick();

        Button next = (Button)findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,thaigifts.class);
                startActivity(i);
            }

        });
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



        downloadJSON("http://seoprojectmarketings.com/android/get_test.php");



    }





    private  void itemclick (){




    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_main, menu);
        return true;
    }*/


//    @Override
//    public boolean onOptionItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id==R.id.action_search){
//            return  true;
//        }
//        return  super.onOptionItemSelected(item);
//    }
//-------------------------------------------------

//--------------------------------------------------------------------------------mysql

    private void downloadJSON(final String urlWebService) {

        class DownloadJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
        Log.d("liwlew2","testbyliw----"+getJSON);
    }

    private void loadIntoListView(String json) throws JSONException {


        JSONArray jsonArray = new JSONArray(json);
         sizesql = jsonArray.length();
        image = new String[sizesql];
        price = new String[sizesql];
        name = new String[sizesql];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            price[i] = obj.optString("price");
            image[i] =  obj.optString("image" );
            name[i] = obj.optString("name");
        }

   /*     ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stocks);
        listvv.setAdapter(arrayAdapter);*/
    }


    //*********************************************************************************************customview


    public class Efficientadapter extends BaseAdapter {
        public Context mContext;
        public LayoutInflater mInflate;
        public Efficientadapter(Context context){
            mContext = context;
            mInflate = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return sizesql;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertview, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if(convertview ==null){
                convertview =mInflate.inflate(R.layout.itemlist, null);
                holder = new ViewHolder();
                holder.description = (TextView) convertview.findViewById(R.id.txtview2);
                holder.authorImagel=(ImageView) convertview.findViewById(R.id.imageview1);
                holder.title=(TextView)convertview.findViewById(R.id.txtview1);
                convertview.setTag(holder);
            }else{
                //rebind widget
                holder = (ViewHolder)convertview.getTag();
            }
            holder.description.setText(price[i].substring(0,(price[i].length())-2)+" บาท");
            holder.title.setText(name[i]);
            //      String url = "http://pordeeshops.com/image/cache/catalog/product1/forman/Image_a71ecfc-1600x1600-200x200.jpg";
            String url = "http://pordeeshops.com/image/"+image[i];
            Glide.with(mContext).load(url).into(holder.authorImagel);
            return convertview;
        }
        public  class  ViewHolder{
            ImageView authorImagel;
            TextView title;

            TextView description;

        }
    }

}
