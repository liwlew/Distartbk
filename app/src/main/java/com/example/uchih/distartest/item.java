package com.example.uchih.distartest;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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

public class item extends AppCompatActivity {
    private int num=1;
    private String []image;
    private ListView listvv ;
    private List<NameValuePair> params;
  private    TextView txt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

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
   // item.DownloadImageTask task = (item.DownloadImageTask) new item.DownloadImageTask((ImageView) findViewById(R.id.imageView))
    //        .execute("http://pordeeshops.com/image/"+datamysql.image_g);

 ImageView image = (ImageView)findViewById(R.id.imageView);
        Picasso.get().load("http://pordeeshops.com/image/"+datamysql.image_g).into(image);
   /* ImageView image = (ImageView)findViewById(R.id.imageView);
        new DownloadImageTask(image).execute("http://pordeeshops.com/image/"+datamysql.image_g);*/

}


        TextView txt1 = (TextView) findViewById(R.id.txt_head);
        txt1.setText(datamysql.name_g);

        TextView txt2 = (TextView) findViewById(R.id.txt_detail);
        txt2.setText("       "+datamysql.detail_g);

        TextView txtprice = (TextView)findViewById(R.id.txt_price);
        txtprice.setText(datamysql.pricr.substring(0,datamysql.pricr.length()-2)+" THB");
//        listvv = (ListView)findViewById(R.id.listtest);



        //downloadJSON("http://seoprojectmarketings.com/android/item_sql.php");
    }


    private void clickbtm(){
        ImageButton next1 = (ImageButton)findViewById(R.id.btm_back);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(item.this,thaigifts.class);
                startActivity(i);
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

                                Toast.makeText(item.this, "Add Cart Success ", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                //set amount item
                if(check){
                    datamysql.list_number.add(num);
                    datamysql.list_cart_image.add(datamysql.image_g);
                    datamysql.list_cart_proid.add(datamysql.productid);
                    datamysql.list_cart_name.add(datamysql.name_g);
                    datamysql.list_cart_price.add(datamysql.pricr);
                    datamysql.arrayck=false;

                    Toast.makeText(item.this, "Add Cart Success", Toast.LENGTH_LONG).show();
                }
              //  Log.d("liwlew","itemtest----"+datamysql.list_cart_image.size());
             //   Log.d("liwlew","itemtest----"+datamysql.list_image.get(0));

                Intent i = new Intent(item.this,thaigifts.class);
                startActivity(i);


            }
        });

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



}
