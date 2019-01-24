package com.example.uchih.distartest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class thaigifts extends AppCompatActivity {
    private   String[]image;
    private   String[]price;
    private   String[] name ;
    private String [] product_id ;
    private  String []meta_description;
    private   VideoView vdoon;
    private  LinearLayout viewall;
    private  int sizesql;
    private GridView griview;
    private  boolean valuem =false;
 //   private  MediaPlayer mp;
    private static ProgressDialog progressDialog;
    private   String urlvdo ="";
    private int check_vdo_on = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thaigifts);

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setBackgroundColor((Color.parseColor("#4CAF50")));
        setSupportActionBar(toolbar);
//---------------------------------------------------------------------search

        final EditText editText_search = (EditText)findViewById(R.id.edit_txt_search);
        editText_search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    datamysql.Search = editText_search.getText().toString();
                    Intent i = new Intent(thaigifts.this,search.class);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
        //----------------------cart-----------------
        Button entercart = (Button)findViewById(R.id.btm_enter_search);
        entercart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(thaigifts.this,addcart.class);
                startActivity(i);
            }
        });
        ///--------------------------------------------------------------------------
//---------------------vdo-------------------
         urlvdo="http://seoprojectmarketings.com/android/vdo/TRK.3gp";
        urlvdo = "http://seoprojectmarketings.com/android/vdo/Cyanide.mp4" ;
        urlvdo = "https://player.twitch.tv/?volume=0.5&!muted&channel=itshafu" ;

     //    urlvdo=" https://www.youtube.com/watch?v=1AszE9-ikOU&start_radio=1&list=RD1AszE9-ikOU";

         vdoon= (VideoView)findViewById(R.id.vdo_on);
        viewall =(LinearLayout)findViewById(R.id.view_all);
//        Uri uri = Uri.parse(urlvdo);
//        vdoon.setVideoURI(uri);
//        vdoon.start();

        progressDialog = ProgressDialog.show(thaigifts.this, "", "Buffering video...", true);
        progressDialog.setCancelable(true);
        PlayVideo();

        WebView vdoweb = (WebView)findViewById(R.id.weviewvdo);
//        vdoweb.setWebViewClient(new WebViewClient());
//        vdoweb.loadUrl(urlvdo);
        //vdoweb.loadUrl("http://seoprojectmarketings.com/android/Ads_Streaming.php");
//        WebSettings webSettings = vdoweb.getSettings();
//        webSettings.setJavaScriptEnabled(true);

        vdoon.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared( MediaPlayer mp) {
                mp.setLooping(true);



                vdoon.setOnTouchListener(new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        setsournd();
                        check_vdo_on=0;
                        //mp.setVolume(0, 0);
                        viewall.setVisibility(View.VISIBLE);
                        return false;
                    }
                });

            }
        });

        //---------------------------------------------


        Button next2 = (Button)findViewById(R.id.button2);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(thaigifts.this,travel.class);
                startActivity(i);
            }
        });
        Button next3 = (Button)findViewById(R.id.button3);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(thaigifts.this,food.class);
                startActivity(i);
            }
        });
        Button next4 = (Button)findViewById(R.id.button4);
        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(thaigifts.this,hotel.class);
                startActivity(i);
            }
        });
        Button test = (Button)findViewById(R.id.btmtest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(thaigifts.this,webview.class);
                startActivity(i);
            }
        });

    /*    Button testsearch = (Button)findViewById(R.id.testsearch);
        testsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(thaigifts.this,search.class);
                startActivity(i);
            }
        });*/

        //*************************data

        //******************************************************

        griview = (GridView)findViewById(R.id.gridview1);
        griview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                datamysql.detail_g = meta_description[position];
                datamysql.name_g = name[position];
                datamysql.image_g = image[position];
                datamysql.productid = product_id[position];
                datamysql.pricr = price[position];
                Intent i = new Intent(thaigifts.this,item.class);
                startActivity(i);

            }
        });

        griview.performClick();


        downloadJSON("http://seoprojectmarketings.com/android/get_test.php");
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu_main, menu);
        return true;
    }*/

//----------------------------------
    public  void setsournd(){
        vdoon.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.setVolume(0, 0);
            }
        });
    }


  /*  MediaPlayer.OnPreparedListener PreparedListener = new MediaPlayer.OnPreparedListener(){

        @Override
        public void onPrepared(MediaPlayer m) {
            try {
                m.setLooping(true);
                if (valuem) {
                    m.setVolume(0f, 0f);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };*/
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
              //  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
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
     //   Log.d("liwlew2","testbyliw----"+getJSON);

    }

    private void loadIntoListView(String json) throws JSONException {

        JSONArray jsonArray = new JSONArray(json);
         sizesql = jsonArray.length();
        image = new String[sizesql];
        price = new String[sizesql];
        name = new String[sizesql];
        meta_description = new String[sizesql];
        product_id =new String[sizesql];

datamysql.list_image = new ArrayList<String>();
        datamysql.list_name = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
         /*   if(obj.optString("price").equals(null)){
                price[i] = "";
            }else{
                price[i] = obj.optString("price");
            }

            if(obj.optString("product_id").equals(null)){
                product_id[i]="";
            }else{
                product_id[i] = obj.optString("product_id");
            }
            if(obj.optString("meta_description").equals(null)){
                meta_description[i]="";
            }else{
                meta_description[i] = obj.optString("meta_description");
            }

            if(obj.optString("image").equals(null)){
                image[i]="";
                datamysql.list_image.add("");
            }else{
                image[i] = obj.optString("image");
                datamysql.list_image.add( obj.optString("image" ));
            }

            if(obj.optString("name").equals(null)){
                name[i]="";
                datamysql.list_name.add("");
            }else{
                name[i] = obj.optString("name");
                datamysql.list_name.add( obj.optString("name" ));
            }*/

            name[i] = obj.optString("name");
            datamysql.list_name.add( obj.optString("name" ));
            image[i] = obj.optString("image");
            datamysql.list_image.add( obj.optString("image" ));
            meta_description[i] = obj.optString("meta_description");
            product_id[i] = obj.optString("product_id");
            price[i] = obj.optString("price");
        }
        griview.setAdapter(new thaigifts.Efficientadapter(getApplicationContext()));
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
            thaigifts.Efficientadapter.ViewHolder holder = null;
            if(convertview ==null){
                convertview =mInflate.inflate(R.layout.itemlist, null);
                holder = new thaigifts.Efficientadapter.ViewHolder();
                holder.description = (TextView) convertview.findViewById(R.id.txtview2);
                holder.authorImagel=(ImageView) convertview.findViewById(R.id.imageview1);
                holder.title=(TextView)convertview.findViewById(R.id.txtview1);
                convertview.setTag(holder);
            }else{
                //rebind widget
                holder = (thaigifts.Efficientadapter.ViewHolder)convertview.getTag();
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
    //-----------------------------vdo-----------------------
    public static final long DISCONNECT_TIMEOUT = 30000; // 5 min = 5 * 60 * 1000 ms
    private Handler disconnectHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // todo
            return true;
        }
    });
    private Runnable disconnectCallback = new Runnable() {
        @Override
        public void run() {
            // Perform any required operation on disconnect
            if(check_vdo_on==0){

                viewall.setVisibility(View.GONE);
                vdoon.setVisibility(View.VISIBLE);
                check_vdo_on=1;
            }


    }
    };

    public void resetDisconnectTimer(){
        disconnectHandler.removeCallbacks(disconnectCallback);
        disconnectHandler.postDelayed(disconnectCallback, DISCONNECT_TIMEOUT);
    }

    public void stopDisconnectTimer(){
        disconnectHandler.removeCallbacks(disconnectCallback);
    }
    @Override
    public void onUserInteraction(){
        resetDisconnectTimer();
    }

    @Override
    public void onResume() {
        super.onResume();
        resetDisconnectTimer();
    }
    @Override
    public void onStop() {
        super.onStop();
        stopDisconnectTimer();
    }
    //-------------------------------------------------------

    private void PlayVideo()
    {
        try
        {
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(thaigifts.this);
            mediaController.setAnchorView(vdoon);

            Uri video = Uri.parse(urlvdo );
            vdoon.setMediaController(mediaController);
            vdoon.setVideoURI(video);
            vdoon.requestFocus();
            vdoon.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
            {

                public void onPrepared(MediaPlayer mp)
                {
                    progressDialog.dismiss();
                    vdoon.start();
                }
            });


        }
        catch(Exception e)
        {
            progressDialog.dismiss();
            System.out.println("Video Play Error :"+e.toString());
            finish();
        }

    }
}
