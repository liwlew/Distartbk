package com.example.uchih.distartest;


import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class food extends AppCompatActivity {
    private GoogleMap mMap;
    private MapFragment mMapFragment;
    private  LatLng sydney = new LatLng(-34, 151);
    private LatLng myHome = new LatLng(13.812528, 100.520800);

    int  sizesql2=0;
    private   String[]image;
    private   double[]v1;
    private double [] v2 ;
    private   String[] name ;

    private  String []detail;
    private int position_image=0;
    private  ArrayList<String> list_location;
    private  String[] id_travel_image;
    private  String []location2;
    private RelativeLayout lv_dteail;
    private     LinearLayout ln_image;
    private     LinearLayout ln_frist;
    private     ListView griview_image;
    private ListView listview;
    private  int sizesql;
    private GridView griview;
    private VideoView vdoon;
    private  LinearLayout viewall;
    private   String urlvdo ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        //toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor((Color.parseColor("#4CAF50")));
        setSupportActionBar(toolbar);

        list_location = new ArrayList<>();


        onclickall();
        num_cart();
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        CountDownTimer cdt = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Tick
            }

            public void onFinish() {
                //----------------------cart-----------------
                Button entercart = (Button)findViewById(R.id.btm_enter_search);
                entercart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(food.this,addcart.class);
                        startActivity(i);
                    }
                });
                ///--------------------------------------------------------------------------


//---------------------------------------------------------------------search

                final EditText editText_search = (EditText)findViewById(R.id.edit_txt_search);
                editText_search.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        // If the event is a key-down event on the "enter" button
                        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                                (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            // Perform action on key press
                            datamysql.Search = editText_search.getText().toString();
                            Intent i = new Intent(food.this,search.class);
                            startActivity(i);
                            return true;
                        }
                        return false;
                    }
                });

                ///------------------------cart--------------------
                entercart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(food.this,addcart.class);
                        startActivity(i);
                    }
                });
                ///--------------------------------------------------------------------------



                Button next2 = (Button)findViewById(R.id.button);
                next2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(food.this,thaigifts.class);
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
                Button test = (Button)findViewById(R.id.btmtest);
                test.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(food.this,login_guide.class);
                        startActivity(i);
                    }
                });
            }
        }.start();

        Button google = (Button)findViewById(R.id.button6);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleMap(sydney , sydney);
            }
        });



        FragmentManager fragMen = getFragmentManager();
        mMapFragment =(MapFragment)fragMen.findFragmentById(R.id.map_frag);
        mMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                String a = "123456";
                // Add a marker in Sydney and move the camera

                sydney = new LatLng(13.7516435, 100.4927041);
                mMap.addMarker(new MarkerOptions().position(sydney).title(""));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12));

             /*   ImageButton btn1 = (ImageButton) findViewById(R.id.imageButton2);
                btn1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        mMap.clear();
                         sydney = new LatLng(13.7516435, 100.4927041);
                        mMap.addMarker(new MarkerOptions().position(sydney).title("วัดพระแก้ว"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
                    }
                });*/



            }
        });


        listview= (ListView) findViewById(R.id.listview_photo);
        griview = (GridView) findViewById(R.id.Listviewtravel);
        lv_dteail = (RelativeLayout) findViewById(R.id.Listviewdetail);
        griview_image = (ListView) findViewById(R.id.Listviewimage);
        ln_image = (LinearLayout) findViewById(R.id.linearimage);
        ln_frist = (LinearLayout) findViewById(R.id.ln_frist);
        griview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                mMap.clear();
                sydney = new LatLng(v1[position], v2[position]);
                mMap.addMarker(new MarkerOptions().position(sydney).title(name[position]));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));



                position_image = position;

                TextView txtfrist = (TextView)findViewById(R.id.txt_frist);
                txtfrist.setText(name[position]);
                sethide();
                ImageView imagefrist = (ImageView)findViewById(R.id.image_frist);
                Picasso.get().load(image[position]).into(imagefrist);
//                food.DownloadImageTask task = (food.DownloadImageTask) new food.DownloadImageTask((ImageView) findViewById(R.id.image_frist))
//                        .execute(image[position]);
               /* griview.setVisibility(View.GONE);
                travel.DownloadImageTask task = (travel.DownloadImageTask) new travel.DownloadImageTask((ImageView) findViewById(R.id.imagedetail))
                        .execute(image[position]);

                griview2.setVisibility(View.VISIBLE);*/
            }
        });

        griview.performClick();
        //--------------------------------------------------------------------------
        /*Button btmbackmenu = (Button) findViewById(R.id.backtomenu);
        btmbackmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView txtname = (TextView)findViewById(R.id.txtname);
                TextView txtdetail = (TextView)findViewById(R.id.txtdetail);
                txtdetail.setText(detail[position_image]);
                txtname.setText(name[position_image]);
                griview2.setVisibility(View.GONE);
                griview.setVisibility(View.VISIBLE);
            }
        });
*/

     /*   final ImageView imageshow = (ImageView) findViewById(R.id.imageshow);

        imagedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mMapFragment.getView().setVisibility(View.GONE);
                imageshow.setVisibility(View.VISIBLE);
                travel.DownloadImageTask task = (travel.DownloadImageTask) new travel.DownloadImageTask((ImageView) findViewById(R.id.imageshow))
                        .execute(image[position_image]);
                lv_dteail.setVisibility(View.GONE);
                griview4.setVisibility(View.VISIBLE);

            }
        });*/
      /*  Button backtodetail = (Button) findViewById(R.id.backtodetail);
        backtodetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                griview2.setVisibility(View.VISIBLE);
                griview4.setVisibility(View.GONE);
                mMapFragment.getView().setVisibility(View.VISIBLE);
            }
        });*/
//---------------
        Button backtodetail = (Button) findViewById(R.id.backtodetail);
        backtodetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lv_dteail.setVisibility(View.GONE);
                ln_image.setVisibility(View.GONE);
                ln_frist.setVisibility(View.VISIBLE);
                mMapFragment.getView().setVisibility(View.VISIBLE);
            }
        });


//---------------------vdo-------------------
        urlvdo = "https://seoprojectmarketings.com/android/vdo/pordee.mp4" ;
        viewall =(LinearLayout)findViewById(R.id.view_all);
        vdoon= (VideoView)findViewById(R.id.vdo_on);
        Uri uri = Uri.parse(urlvdo);
        vdoon.setVideoURI(uri);
        vdoon.start();
        vdoon.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared( MediaPlayer mp) {
                mp.setLooping(true);
                vdoon.setOnTouchListener(new View.OnTouchListener()
                {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        datamysql.check_vdo_on = 0;
                        //mp.setVolume(0, 0);
                        viewall.setVisibility(View.VISIBLE);
                        return false;
                    }
                });

            }
        });

        //---------------------------------------------
        setlang();
        language();
        setcolor();
//-----------------------------------------------------------------------------
        downloadJSON("https://mannature.com/test/food.php");
        downloadJSON2("https://mannature.com/test/food_image.php");
    }
    public void sethide(){
        TextView txt_selete = (TextView)findViewById(R.id.txt_selete);
        ImageView image_selete = (ImageView)findViewById(R.id.image_selete);

        TextView txt_frist = (TextView)findViewById(R.id.txt_frist);
        ImageView image_frist = (ImageView)findViewById(R.id.image_frist);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        Button btn_first =(Button)findViewById(R.id.btn_first);

        txt_selete.setVisibility(View.GONE);
        image_selete.setVisibility(View.GONE);

        txt_frist.setVisibility(View.VISIBLE);
        image_frist.setVisibility(View.VISIBLE);
        ratingBar.setVisibility(View.VISIBLE);
        btn_first.setVisibility(View.VISIBLE);

        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtfrist = (TextView)findViewById(R.id.txtname);
                txtfrist.setText(name[position_image]);

                TextView txtdetail = (TextView)findViewById(R.id.txtdetail);

                txtdetail.setText("      "+detail[position_image]);
                list_location.clear();
                findimage();
                mMapFragment.getView().setVisibility(View.GONE);
                lv_dteail.setVisibility(View.VISIBLE);
                ln_frist.setVisibility(View.GONE);
                ln_image.setVisibility(View.VISIBLE);

            }
        });


    }
    public void language(){
        final Button chiha =(Button)findViewById(R.id.btm_lang_china);
        final  Button eng =(Button)findViewById(R.id.btm_lang_eng);
        final Button thai =(Button)findViewById(R.id.btm_lang_thai);

        thai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datamysql.langued = 2 ;
                setlang();
            }
        });

        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datamysql.langued = 1 ;

                setlang();
            }
        });

        chiha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datamysql.langued = 3 ;
                setlang();
            }
        });
    }
    public  void setcolor(){
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rat1);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.parseColor("#EC933B"), PorterDuff.Mode.SRC_ATOP);


        RatingBar ratingBar2 = (RatingBar) findViewById(R.id.rat2);
        LayerDrawable stars2 = (LayerDrawable) ratingBar2.getProgressDrawable();
        stars2.getDrawable(2).setColorFilter(Color.parseColor("#EC933B"), PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar3 = (RatingBar) findViewById(R.id.rat3);
        LayerDrawable stars3 = (LayerDrawable) ratingBar3.getProgressDrawable();
        stars3.getDrawable(2).setColorFilter(Color.parseColor("#EC933B"), PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar4 = (RatingBar) findViewById(R.id.rat4);
        LayerDrawable stars4 = (LayerDrawable) ratingBar4.getProgressDrawable();
        stars4.getDrawable(2).setColorFilter(Color.parseColor("#EC933B"), PorterDuff.Mode.SRC_ATOP);

        RatingBar ratingBar5 = (RatingBar) findViewById(R.id.rat5);
        LayerDrawable stars5 = (LayerDrawable) ratingBar5.getProgressDrawable();
        stars5.getDrawable(2).setColorFilter(Color.parseColor("#EC933B"), PorterDuff.Mode.SRC_ATOP);
    }
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
    private void openGoogleMap(LatLng src, LatLng dest) {
        String url = "http://maps.google.com/maps?saddr="+src.latitude+","+src.longitude+"&daddr="+dest.latitude+","+dest.longitude+"&mode=driving";
        Uri gmmIntentUri = Uri.parse(url);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
    private void onclickall(){
     /*   Button next = (Button)findViewById(R.id.buttonNormal);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(travel.this,tv_detail.class);
                startActivity(i);
            }
        });*/
    }
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
        // Log.d("liwlew2","testbyliw----"+getJSON);
    }

    private void loadIntoListView(String json) throws JSONException {


        JSONArray jsonArray = new JSONArray(json);
//        Log.d("foodtest","testbyliw----"+jsonArray.length());

            sizesql = jsonArray.length();
            image = new String[sizesql];
            v1 = new double[sizesql];
            v2 = new double[sizesql];
            name = new String[sizesql];

            detail = new String[sizesql];
            int lang = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getString("language").equals("th")) {
                    lang = 2;
                } else if (obj.getString("language").equals("eng")) {
                    lang = 1;
                } else if (obj.getString("language").equals("cn")) {
                    lang = 3;
                }
                if (datamysql.langued == lang) {
                    image[i] = obj.getString("location_image");
                    name[i] = obj.getString("name");
                    v1[i] = Double.parseDouble(obj.getString("v"));
                    v2[i] = Double.parseDouble(obj.getString("v1"));
                    detail[i] = obj.getString("detail");
                }
            }
            griview.setAdapter(new food.Efficientadapter(getApplicationContext()));

   /*     ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stocks);
        listvv.setAdapter(arrayAdapter);*/
//----------------------------------
      /*  ImageView imagefrist = (ImageView)findViewById(R.id.image_frist);
        Picasso.get().load(image[0]).into(imagefrist);
        TextView txtfrist = (TextView)findViewById(R.id.txt_frist);
        txtfrist.setText(name[0]);*/
//-------------------------------------
    }


    //--------------------------------------------------------------------------------mysql2

    private void downloadJSON2(final String urlWebService) {

        class DownloadJSON2 extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //  Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView2(s);
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
        DownloadJSON2 getJSON = new DownloadJSON2();
        getJSON.execute();
        // Log.d("liwlew2","testbyliw----"+getJSON);
    }

    private void loadIntoListView2(String json) throws JSONException {


        JSONArray jsonArray = new JSONArray(json);
        sizesql2 = jsonArray.length();
        id_travel_image = new String[sizesql2];
        location2 = new String[sizesql2];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            id_travel_image[i] =  obj.getString("id_food" );
            location2[i] =  obj.getString("location_image2" );
            if(id_travel_image[i].equals("1")){
                list_location.add(obj.getString("location_image2" ));
            }

        }

        //imageclickfirst();
        listview.setAdapter(new food.Efficientadapter2(getApplicationContext()));
//-------------------------------------
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
            food.Efficientadapter.ViewHolder holder = null;
            if(convertview ==null){
                convertview =mInflate.inflate(R.layout.travel_list, null);
                holder = new food.Efficientadapter.ViewHolder();

                holder.authorImagel=(ImageView) convertview.findViewById(R.id.imbtmtravel);
                holder.title=(TextView)convertview.findViewById(R.id.txtviewtravel);

                convertview.setTag(holder);
            }else{
                //rebind widget
                holder = (food.Efficientadapter.ViewHolder)convertview.getTag();
            }

            holder.title.setText(name[i]);
            //      String url = "http://pordeeshops.com/image/cache/catalog/product1/forman/Image_a71ecfc-1600x1600-200x200.jpg";
            String url = image[i];
            Glide.with(mContext).load(url).into(holder.authorImagel);
            return convertview;
        }
        public  class  ViewHolder{
            ImageView authorImagel;
            TextView title;
            TextView description;

        }
    }
    //*********************************************************************************************customview2
    public class Efficientadapter2 extends BaseAdapter {
        public Context mContext;
        public LayoutInflater mInflate;
        public Efficientadapter2(Context context){
            mContext = context;
            mInflate = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return list_location.size();
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
        public View getView(final int i, View convertview, ViewGroup viewGroup) {
            food.Efficientadapter2.ViewHolder holder = null;
            if(convertview ==null){
                convertview =mInflate.inflate(R.layout.list_photomap, null);
                holder = new food.Efficientadapter2.ViewHolder();

                holder.authorImagel=(ImageView) convertview.findViewById(R.id.photomap);
                convertview.setTag(holder);
            }else{
                //rebind widget
                holder = (food.Efficientadapter2.ViewHolder)convertview.getTag();
            }
            String url =list_location.get(i);
            Glide.with(mContext).load(url).into(holder.authorImagel);

            return convertview;
        }
        public  class  ViewHolder{
            ImageView authorImagel;
            TextView title;
            TextView price_item;
            TextView numberitem;
            TextView total;
        }
    }

    public  void  imageclickfirst(){
        ImageView imagfrist = (ImageView)findViewById(R.id.image_frist);
        imagfrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView txtfrist = (TextView)findViewById(R.id.txtname);
                txtfrist.setText(name[position_image]);

                TextView txtdetail = (TextView)findViewById(R.id.txtdetail);

                txtdetail.setText("      "+detail[position_image]);
                list_location.clear();
                findimage();
/*
               travel.DownloadImageTask task2 = (travel.DownloadImageTask) new travel.DownloadImageTask((ImageView) findViewById(R.id.image_1))
                        .execute(location2[ a1[0]]);
                travel.DownloadImageTask task3 = (travel.DownloadImageTask) new travel.DownloadImageTask((ImageView) findViewById(R.id.image_2))
                        .execute(location2[ a1[1]]);
                travel.DownloadImageTask task4 = (travel.DownloadImageTask) new travel.DownloadImageTask((ImageView) findViewById(R.id.image_3))
                        .execute(location2[ a1[2]]);
                travel.DownloadImageTask task5 = (travel.DownloadImageTask) new travel.DownloadImageTask((ImageView) findViewById(R.id.image_4))
                        .execute(location2[ a1[3]]);
                travel.DownloadImageTask task6 = (travel.DownloadImageTask) new travel.DownloadImageTask((ImageView) findViewById(R.id.image_5))
                        .execute(location2[ a1[4]]);*/

//----------------------------------------------------------------------------------
                mMapFragment.getView().setVisibility(View.GONE);
                lv_dteail.setVisibility(View.VISIBLE);
                ln_frist.setVisibility(View.GONE);
                ln_image.setVisibility(View.VISIBLE);
            }
        });
    }
    public  void findimage(){
        //----------------------------------------------find image-------------------
        int []a1 = new int[5];
        int a=0;
        listview.setAdapter(new food.Efficientadapter2(getApplicationContext()));
        for(int i = 0;i<id_travel_image.length;i++){

            //       Log.d("travel","testbyliw---- yes"+ id_travel_image.length+"  [[ "+id_travel_image[i] );
            if(id_travel_image[i].equals((position_image+1)+"")){
                list_location.add(location2[i]);
                /*a1[a]=i;
                a++;*/

            }

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
    public  void setlang(){
        final Button chiha =(Button)findViewById(R.id.btm_lang_china);
        final  Button eng =(Button)findViewById(R.id.btm_lang_eng);
        final Button thai =(Button)findViewById(R.id.btm_lang_thai);

        final EditText txt_search =(EditText)findViewById(R.id.edit_txt_search);
        final Button thaigift =(Button)findViewById(R.id.button);
        final Button travel =(Button)findViewById(R.id.button2);
        final Button food =(Button)findViewById(R.id.button3);
        final Button hotel =(Button)findViewById(R.id.button4);
        downloadJSON("http://seoprojectmarketings.com/android/food.php");
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

        }else if(datamysql.langued == 2){
            chiha.setBackgroundResource(R.drawable.chiha_bw);
            thai.setBackgroundResource(R.drawable.thai_color);
            eng.setBackgroundResource(R.drawable.eng_bw);
            txt_search.setHint("ค้นหาสินค้า");
            thaigift.setText("ของขวัญไทย");
            travel.setText("ท่องเที่ยว");
            food.setText("อาหาร");
            hotel.setText("โรงแรม");
        }else  if(datamysql.langued == 3){
            chiha.setBackgroundResource(R.drawable.chiha_color);
            thai.setBackgroundResource(R.drawable.thai_bw);
            eng.setBackgroundResource(R.drawable.eng_bw);
            txt_search.setHint("搜索产品");
            thaigift.setText("泰国礼物");
            travel.setText("旅行");
            food.setText("餐饮");
            hotel.setText("旅馆");
        }
    }
}
