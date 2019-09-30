package com.example.uchih.distartest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class category extends AppCompatActivity {
    private   String[]image;
    private   String[]price;
    private   String[] name ;
    private String [] product_id ;
    private  String []meta_description;
    private   VideoView vdoon;
    private  LinearLayout viewall;
    private   String urlvdo ="";
    private  int sizesql;
    private GridView griview;
    private int num=1;
    public static int viewint  = 0;
    private  boolean valuem =false;
    private String  catesql ="";

    private String url = "https://mannature.com/test/get_test.php";
    //   private  MediaPlayer mp;
    private    TextView txt ;
    private static ProgressDialog progressDialog;
    private ConstraintLayout itemview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

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
                    Intent i = new Intent(category.this,search.class);
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
                Intent i = new Intent(category.this,addcart.class);
                startActivity(i);
            }
        });
        ///--------------------------------------------------------------------------
        //---------------------------
        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

//---------------------vdo-------------------
//         urlvdo="http://seoprojectmarketings.com/android/vdo/TRK.3gp";
//        urlvdo = "http://seoprojectmarketings.com/android/vdo/pordee.mp4" ;
        urlvdo = "http://seoprojectmarketings.com/android/vdo/pordee.mp4" ;
        //    urlvdo = "https://player.twitch.tv/?volume=0.5&!muted&channel=itshafu" ;

        //    urlvdo=" https://www.youtube.com/watch?v=1AszE9-ikOU&start_radio=1&list=RD1AszE9-ikOU";
        viewall =(LinearLayout)findViewById(R.id.view_all);
        vdoon= (VideoView)findViewById(R.id.vdo_on);
        Uri uri = Uri.parse(urlvdo);
        vdoon.setVideoURI(uri);
        vdoon.start();
        if(datamysql.vdoview.equals("")){
            viewall.setVisibility(View.GONE);

            datamysql.vdoview="1";
        }

//        progressDialog = ProgressDialog.show(thaigifts.this, "", "Buffering video...", true);
//        progressDialog.setCancelable(true);
//        PlayVideo();

        WebView vdoweb = (WebView)findViewById(R.id.weviewvdo);
//        vdoweb.setWebViewClient(new WebViewClient());
//        vdoweb.loadUrl(urlvdo);
        //vdoweb.loadUrl("http://seoprojectmarketings.com/android/Ads_Streaming.php");
//        WebSettings webSettings = vdoweb.getSettings();
//        webSettings.setJavaScriptEnabled(true);
        itemview = (ConstraintLayout)findViewById(R.id.itemview);
        vdoon.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared( MediaPlayer mp) {
                mp.setLooping(true);



                vdoon.setOnTouchListener(new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        //     setsournd();

                        datamysql.check_vdo_on = 0;
                        //mp.setVolume(0, 0);
                        if(viewint==0){
                            viewall.setVisibility(View.VISIBLE);
                        }else if(viewint==1){
                            itemview.setVisibility(View.VISIBLE);
                        }

                        //     itemview.setVisibility(View.VISIBLE);
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
                Intent i = new Intent(category.this,travel.class);
                startActivity(i);
            }
        });
        Button next3 = (Button)findViewById(R.id.button3);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(category.this,food.class);
                startActivity(i);
            }
        });
        Button next4 = (Button)findViewById(R.id.button4);
        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(category.this,hotel.class);
                startActivity(i);
            }
        });
        Button test = (Button)findViewById(R.id.btmtest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(category.this,webview.class);
                startActivity(i);
            }
        });
        Button category = (Button)findViewById(R.id.category);
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(category.this,category.class);
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
                //Toast.makeText(getApplicationContext(), datamysql.image_g , Toast.LENGTH_SHORT).show();
             /*   Intent i = new Intent(category.this,item.class);
                startActivity(i);*/
                item();
                viewint = 1;
                itemview.setVisibility(View.VISIBLE);
                viewall.setVisibility(View.GONE);

            }
        });

        griview.performClick();
        setcate();
        num_cart();
        setlang();
        ShowData();
        language();
        // downloadJSON("http://seoprojectmarketings.com/android/get_test.php");
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
  /*//--------------------------------------------------------------------------------mysql

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
         *//*   if(obj.optString("price").equals(null)){
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
            }*//*

            name[i] = obj.optString("name");
         //   datamysql.list_name.add( obj.optString("name" ));
            image[i] = obj.optString("image");
           // datamysql.list_image.add( obj.optString("image" ));
            meta_description[i] = obj.optString("meta_description");
            product_id[i] = obj.optString("product_id");
            price[i] = obj.optString("price");
        }
        griview.setAdapter(new thaigifts.Efficientadapter(getApplicationContext()));

    }
*/

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
            category.Efficientadapter.ViewHolder holder = null;
            if(convertview ==null){
                convertview =mInflate.inflate(R.layout.itemlist, null);
                holder = new category.Efficientadapter.ViewHolder();
                holder.description = (TextView) convertview.findViewById(R.id.txtview2);
                holder.authorImagel=(ImageView) convertview.findViewById(R.id.imageview1);
                holder.title=(TextView)convertview.findViewById(R.id.txtview1);
                convertview.setTag(holder);
            }else{
                //rebind widget
                holder = (category.Efficientadapter.ViewHolder)convertview.getTag();
            }
            holder.description.setText(price[i].substring(0,(price[i].length())-2)+" บาท");
            holder.title.setText(name[i]);
            // String url = "http://pordeeshops.com/image/cache/catalog/product1/forman/Image_a71ecfc-1600x1600-200x200.jpg";
            String url = "http://kaaidee.com/image/"+image[i];
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
            if(datamysql.check_vdo_on==0){


                viewall.setVisibility(View.GONE);
                itemview.setVisibility(View.GONE);
                //   vdoon.setVisibility(View.VISIBLE);
                datamysql.check_vdo_on=1;
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


    public  void num_cart(){
        TextView txtnum_cart = (TextView)findViewById(R.id.txt_cart);
        //error
        if(datamysql.numcart_>0){
            txtnum_cart.setVisibility(View.VISIBLE);
            txtnum_cart.setText(datamysql.numcart_+"");
        }
    }
    public  void item(){
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        txt = (TextView)findViewById(R.id.txtview0);
        final Button add = (Button)findViewById(R.id.btmadd0);
        final Button delete = (Button)findViewById(R.id.btmdelete);
        /*  */
        TextView  txt_price= (TextView) findViewById(R.id.txtprice);

        txt_price.setText(datamysql.pricr.substring(0,datamysql.pricr.length()-2)+" THB");


        clickbtm();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tt = (String) txt.getText().toString();
                num = Integer.parseInt(tt);
                tt = ""+(num+1);
                txt.setText(tt);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tt = (String) txt.getText().toString();
                num = Integer.parseInt(tt);
                if(num<=1){
                    num=2;
                }
                tt = ""+(num-1);

                txt.setText(tt);
            }
        });


        if(!datamysql.image_g.equals("")){
            try {
                category.DownloadImageTask task = (category.DownloadImageTask) new category.DownloadImageTask((ImageView) findViewById(R.id.imageView))
                        .execute("http://kaaidee.com/image/"+datamysql.image_g);
      /*  ImageView image = (ImageView)findViewById(R.id.imageView);
        Picasso.get().load("http://kaaidee.com/image/"+datamysql.image_g).into(image);*/

            } catch (Exception e) {
                e.printStackTrace();
            }

   /* ImageView image = (ImageView)findViewById(R.id.imageView);
        new DownloadImageTask(image).execute("http://kaaidee.com/image/"+datamysql.image_g);*/

        }
        TextView txt1 = (TextView) findViewById(R.id.txt_head);
        txt1.setText(datamysql.name_g);

        TextView txt2 = (TextView) findViewById(R.id.txt_detail);
        txt2.setText("       "+datamysql.detail_g);

        TextView txtprice = (TextView)findViewById(R.id.txt_price);
        txtprice.setText(datamysql.pricr.substring(0,datamysql.pricr.length()-2)+" THB");
    }

    private void clickbtm(){
        txt = (TextView)findViewById(R.id.txtview0);
        ImageButton next1 = (ImageButton)findViewById(R.id.btm_back);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("1");
                category.DownloadImageTask task = (category.DownloadImageTask) new category.DownloadImageTask((ImageView) findViewById(R.id.imageView))
                        .execute("");
                viewint=0;
                itemview.setVisibility(View.GONE);
                viewall.setVisibility(View.VISIBLE);
            }
        });

        final AlertDialog.Builder ad = new AlertDialog.Builder(this);
        datamysql.list_cart = new ArrayList<String>();
        datamysql.list_productid = new ArrayList<String>();
        if(datamysql.arrayck) {
            datamysql.list_cart_proid = new ArrayList<String>();
            datamysql.list_cart_image = new ArrayList<String>();
            datamysql.list_cart_name = new ArrayList<String>();
            datamysql.list_number = new ArrayList<Integer>();
            datamysql.list_cart_price = new ArrayList<>();
            datamysql.list_cart_check = new ArrayList<>();
        }

        Button next2 = (Button)findViewById(R.id.btm_addcart);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//*************
                datamysql newdata = new datamysql();
                //-----------
                boolean check = true;
                int num = Integer.parseInt( txt.getText().toString()) ;
                if(datamysql.list_cart_proid.size()>0){
                    for(int i =0;i<datamysql.list_cart_proid.size();i++){

                        if(datamysql.list_cart_proid.get(i).equals(datamysql.productid)){
                            check=false;
                            datamysql.list_number.set(i,num+datamysql.list_number.get(i));

                            Toast.makeText(category.this, "Add Cart Success ", Toast.LENGTH_LONG).show();
                        }
                    }
                    //  datamysql.numcart_ = datamysql.numcart_ +  1;
                }
                //set amount item
                if(check){
                    datamysql.list_number.add(num);
                    datamysql.list_cart_image.add(datamysql.image_g);
                    datamysql.list_cart_proid.add(datamysql.productid);
                    datamysql.list_cart_name.add(datamysql.name_g);
                    datamysql.list_cart_price.add(datamysql.pricr);
                    datamysql.list_cart_check.add(true);
                    datamysql.arrayck=false;
                    datamysql.numcart_ = datamysql.numcart_ +  1;
                    Toast.makeText(category.this, "Add Cart Success", Toast.LENGTH_LONG).show();
                }

                //  Log.d("liwlew","itemtest----"+datamysql.list_cart_image.size());
                //   Log.d("liwlew","itemtest----"+datamysql.list_image.get(0));


                category.DownloadImageTask task = (category.DownloadImageTask) new category.DownloadImageTask((ImageView) findViewById(R.id.imageView))
                        .execute("");
                num_cart();
                viewint=0;
                itemview.setVisibility(View.GONE);
                viewall.setVisibility(View.VISIBLE);
                txt.setText("1");
            }
        });

    }
    //-------------------
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
//-------------------

    public void language(){
        final Button chiha =(Button)findViewById(R.id.btm_lang_china);
        final  Button eng =(Button)findViewById(R.id.btm_lang_eng);
        final Button thai =(Button)findViewById(R.id.btm_lang_thai);

        thai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datamysql.langued = 2 ;
                ShowData();
                setlang();
            }
        });

        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datamysql.langued = 1 ;
                ShowData();
                setlang();
            }
        });

        chiha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datamysql.langued = 3 ;
                setlang();
                ShowData();
            }
        });






    }
    public void  setcate(){
//        final Button cateall =(Button)findViewById(R.id.cateall);
//        final  Button cate1 =(Button)findViewById(R.id.cate1);
//        final  Button cate2 =(Button)findViewById(R.id.cate2);

       /* cateall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "http://seoprojectmarketings.com/android/get_test.php";
                ShowData();

            }
        });
        cate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "http://seoprojectmarketings.com/android/android_cata.php";
                catesql = "131";
                ShowData();
            }
        });
        cate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = "http://seoprojectmarketings.com/android/android_cata.php";
                catesql = "134";
                ShowData();
            }
        });*/

    }


    public  void setlang(){
        final Button chiha =(Button)findViewById(R.id.btm_lang_china);
        final  Button eng =(Button)findViewById(R.id.btm_lang_eng);
        final Button thai =(Button)findViewById(R.id.btm_lang_thai);

        final EditText txt_search =(EditText)findViewById(R.id.edit_txt_search);
        final Button thaigift =(Button)findViewById(R.id.button);
        final Button travel =(Button)findViewById(R.id.button2);
        final Button food =(Button)findViewById(R.id.button3);
        final Button hotel =(Button)findViewById(R.id.button4);


//        final Button catall =(Button)findViewById(R.id.cateall);
//        final Button cat1 =(Button)findViewById(R.id.cate1);
//        final Button cat2 =(Button)findViewById(R.id.cate2);

        //android:hint="Seach product"
        if (datamysql.langued == 1) {
            chiha.setBackgroundResource(R.drawable.chiha_bw);
            thai.setBackgroundResource(R.drawable.thai_bw);
            eng.setBackgroundResource(R.drawable.eng_color);
            txt_search.setHint("Search product");
            thaigift.setText("Thai Gifts");
            travel.setText("travel");
            food.setText("food");
            hotel.setText("hotel");

//            catall.setText("all");
//            cat1.setText("Health");
//            cat2.setText("Groceries");

        }else if(datamysql.langued == 2){
            chiha.setBackgroundResource(R.drawable.chiha_bw);
            thai.setBackgroundResource(R.drawable.thai_color);
            eng.setBackgroundResource(R.drawable.eng_bw);
            txt_search.setHint("ค้นหาสินค้า");
            thaigift.setText("ของขวัญไทย");
            travel.setText("ท่องเที่ยว");
            food.setText("อาหาร");
            hotel.setText("โรงแรม");

//            catall.setText("ทั้งหมด");
//            cat1.setText("สุขภาพและความงาม");
//            cat2.setText("ซุปเปอร์มาเก็ต");
        }else  if(datamysql.langued == 3){
            chiha.setBackgroundResource(R.drawable.chiha_color);
            thai.setBackgroundResource(R.drawable.thai_bw);
            eng.setBackgroundResource(R.drawable.eng_bw);
            txt_search.setHint("搜索产品");
            thaigift.setText("泰国礼物");
            travel.setText("旅行");
            food.setText("餐饮");
            hotel.setText("旅馆");

//            catall.setText("所有");
//            cat1.setText("健康");
//            cat2.setText("杂货");
        }
    }
    public void ShowData()
    {
        // listView1
        //   final ListView lisView1 = (ListView)findViewById(R.id.listView1);

        // keySearch
        //  EditText strKeySearch = (EditText)findViewById(R.id.txtKeySearch);

        // Disbled Keyboard auto focus
          /*  InputMethodManager imm = (InputMethodManager)getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(strKeySearch.getWindowToken(), 0);*/

        //  String url = "http://seoprojectmarketings.com/android/get_test.php";



        // Paste Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("txtlang", datamysql.langued+""));
        params.add(new BasicNameValuePair("txtcate", catesql));
        try {
            JSONArray data = new JSONArray(getJSONUrl(url,params));
            sizesql = data.length();
            image = new String[sizesql];
            price = new String[sizesql];
            name = new String[sizesql];
            meta_description = new String[sizesql];
            product_id =new String[sizesql];

            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);


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

                name[i] = c.optString("name");
                //   datamysql.list_name.add( obj.optString("name" ));
                image[i] = c.optString("image");
                // datamysql.list_image.add( obj.optString("image" ));
                meta_description[i] = c.optString("meta_description");
                product_id[i] = c.optString("product_id");
                price[i] = c.optString("price");

            }
            griview.setAdapter(new category.Efficientadapter(getApplicationContext()));
            //    lisView1.setAdapter(new ImageAdapter(this));

            //   registerForContextMenu(lisView1);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String getJSONUrl(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Download OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }


}
