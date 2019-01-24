package com.example.uchih.distartest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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

public class login_guide extends AppCompatActivity {
    private String id ;
    private String name = "";
    private String pass;
    private  boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guide);

        //--------------------------------------------------------------------------


        // txtUsername & txtPassword


        // btnLogin
        final Button btnLogin = (Button) findViewById(R.id.btnlogin);
        // Perform action on click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                downloadJSON("http://seoprojectmarketings.com/android/guide.php");


            }

        });


    }



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
        //Log.d("liwlew2","testbyliw----"+getJSON);
    }

    private void loadIntoListView(String json) throws JSONException {
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);
        JSONArray jsonArray = new JSONArray(json);
        final EditText txtUser = (EditText)findViewById(R.id.edtxt1);
        final EditText txtPass = (EditText)findViewById(R.id.edtxt2);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if(txtUser.getText().toString().equals(obj.optString("id" )) && txtPass.getText().toString().equals(obj.optString("password" ))  ){
                name =  obj.optString("name" );
                Toast.makeText(login_guide.this, "Login Success "+"\n"+"Hi "+name, Toast.LENGTH_SHORT).show();

                isertdb();
                Intent newActivity = new Intent(login_guide.this,thaigifts.class);
                startActivity(newActivity);

            }

        }
        if(name.equals("")){

            ad.setTitle("Error! ");
            ad.setIcon(android.R.drawable.btn_star_big_on);
            ad.setPositiveButton("Close", null);
            ad.setMessage("try again");
            ad.show();
            txtUser.setText("");
            txtPass.setText("");
            Toast.makeText(login_guide.this, "try again ", Toast.LENGTH_SHORT).show();

        }

//        griview.setAdapter(new thaigifts.Efficientadapter(getApplicationContext()));
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stocks);
//        listvv.setAdapter(arrayAdapter);
    }

    public  void isertdb(){


    }




}
