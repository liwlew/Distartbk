package com.example.uchih.distartest;


import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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


public class hotel extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        //toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor((Color.parseColor("#4CAF50")));
        setSupportActionBar(toolbar);
        //----------------------cart-----------------
        Button entercart = (Button)findViewById(R.id.btm_enter_search);
        entercart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(hotel.this,addcart.class);
                startActivity(i);
            }
        });
        ///--------------------------------------------------------------------------

        list_location = new ArrayList<>();
//---------------------------------------------------------------------search

        final EditText editText_search = (EditText)findViewById(R.id.edit_txt_search);
        editText_search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    datamysql.Search = editText_search.getText().toString();
                    Intent i = new Intent(hotel.this,search.class);
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

                Intent i = new Intent(hotel.this,addcart.class);
                startActivity(i);
            }
        });
        ///--------------------------------------------------------------------------

        onclickall();

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        Button next2 = (Button)findViewById(R.id.button);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(hotel.this,thaigifts.class);
                startActivity(i);
            }
        });
        Button next3 = (Button)findViewById(R.id.button3);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(hotel.this,food.class);
                startActivity(i);
            }
        });
        Button next4 = (Button)findViewById(R.id.button4);
        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(hotel.this,hotel.class);
                startActivity(i);
            }
        });
        Button test = (Button)findViewById(R.id.btmtest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(hotel.this,login_guide.class);
                startActivity(i);
            }
        });
     /*   Button testcart = (Button)findViewById(R.id.btmtestcart);
        testcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(traveListviewimagel.this,addcart.class);
                startActivity(i);
            }
        });*/


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
                mMap.addMarker(new MarkerOptions().position(sydney).title("วัดพระแก้ว"));
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
                ImageView imagefrist = (ImageView)findViewById(R.id.image_frist);
                Picasso.get().load(image[position]).into(imagefrist);
               /* hotel.DownloadImageTask task = (hotel.DownloadImageTask) new hotel.DownloadImageTask((ImageView) findViewById(R.id.image_frist))
                        .execute(image[position]);*/
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




        setcolor();
//-----------------------------------------------------------------------------
        downloadJSON("http://seoprojectmarketings.com/android/food.php");
        downloadJSON2("http://seoprojectmarketings.com/android/travel_image.php");
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
        sizesql = jsonArray.length();
        image = new String[sizesql];
        v1 = new double[sizesql];
        v2 = new double[sizesql];
        name = new String[sizesql];

        detail =new String[sizesql];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            image[i] =  obj.getString("location_image" );
            name[i] = obj.getString("name");
            v1[i] = Double.parseDouble(obj.getString("v"));
            v2[i] = Double.parseDouble(obj.getString("v1"));
            detail[i]=obj.getString("detail");
        }
        griview.setAdapter(new hotel.Efficientadapter(getApplicationContext()));

   /*     ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stocks);
        listvv.setAdapter(arrayAdapter);*/
//----------------------------------
        ImageView imagfrist = (ImageView)findViewById(R.id.image_frist);
        TextView txtfrist = (TextView)findViewById(R.id.txt_frist);
        hotel.DownloadImageTask task = (hotel.DownloadImageTask) new hotel.DownloadImageTask((ImageView) findViewById(R.id.image_frist))
                .execute(image[0]);
        txtfrist.setText(name[0]);
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
            id_travel_image[i] =  obj.getString("id_travel" );
            location2[i] =  obj.getString("location_image2" );
            if(id_travel_image[i].equals("1")){
                list_location.add(obj.getString("location_image2" ));
            }

        }

        imageclickfirst();
        listview.setAdapter(new hotel.Efficientadapter2(getApplicationContext()));
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
            hotel.Efficientadapter.ViewHolder holder = null;
            if(convertview ==null){
                convertview =mInflate.inflate(R.layout.travel_list, null);
                holder = new hotel.Efficientadapter.ViewHolder();

                holder.authorImagel=(ImageView) convertview.findViewById(R.id.imbtmtravel);
                holder.title=(TextView)convertview.findViewById(R.id.txtviewtravel);

                convertview.setTag(holder);
            }else{
                //rebind widget
                holder = (hotel.Efficientadapter.ViewHolder)convertview.getTag();
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
            hotel.Efficientadapter2.ViewHolder holder = null;
            if(convertview ==null){
                convertview =mInflate.inflate(R.layout.list_photomap, null);
                holder = new hotel.Efficientadapter2.ViewHolder();

                holder.authorImagel=(ImageView) convertview.findViewById(R.id.photomap);
                convertview.setTag(holder);
            }else{
                //rebind widget
                holder = (hotel.Efficientadapter2.ViewHolder)convertview.getTag();
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
        listview.setAdapter(new hotel.Efficientadapter2(getApplicationContext()));
        for(int i = 0;i<id_travel_image.length;i++){

            //       Log.d("travel","testbyliw---- yes"+ id_travel_image.length+"  [[ "+id_travel_image[i] );
            if(id_travel_image[i].equals((position_image+1)+"")){
                list_location.add(location2[i]);
                /*a1[a]=i;
                a++;*/

            }

        }
    }
}
