package com.example.uchih.distartest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class item extends AppCompatActivity {
    private int num=0;
    private ListView listvv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        final EditText txt = (EditText)findViewById(R.id.txtview0);
        final Button add = (Button)findViewById(R.id.btmadd0);
       final Button delete = (Button)findViewById(R.id.btmdelete);
       /*  */
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


        item.DownloadImageTask task = (item.DownloadImageTask) new item.DownloadImageTask((ImageView) findViewById(R.id.imageView))
                .execute("http://pordeeshops.com/image/cache/catalog/product1/forman/Image_6a3d794-1600x1600-1600x1600.jpg");
        listvv = (ListView)findViewById(R.id.listtest);


      //  downloadJSON("http://seoprojectmarketings.com/android/get_test.php?status=1");
       // downloadJSON("http://seoprojectmarketings.com/android/get_test.php");
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



/*    private void downloadJSON(final String urlWebService) {

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
        String[] stocks = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            stocks[i] =  obj.optString("product_id" + "");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stocks);
        listvv.setAdapter(arrayAdapter);
    }*/
}
