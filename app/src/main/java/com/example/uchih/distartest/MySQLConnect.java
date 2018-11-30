package com.example.uchih.distartest; //เปลี่ยนชื่อ package เป็นของแอพตัวเอง

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnect {

    private final Activity main;
    private List<String> list;
    private List<String> list2;
    public String URL = "http://seoprojectmarketings.com", GET_URL = "/android/get_post.php?status=1", SENT_URL = "android/sent_post.php";
    //public String URL = "127.0.0.1/"; หากรันบน emulator ให้ใช้ IP 10.0.2.2

    public MySQLConnect(){
        main = null;
    }

    public MySQLConnect(Activity mainA){
        main = mainA;
        list = new ArrayList<String>();
        list2 = new ArrayList<String>();
    }

    public List<String> getData() {

        String url = URL + GET_URL;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
                Toast.makeText(main, list.get(0),Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(main,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);

        return list;
    }

    private void showJSON(String response) {
        String comment = "";
        String price = "";
        try {
//            JSONObject jsonObject2 = new JSONObject(response);
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
//            JSONArray result2 = jsonObject2.getJSONArray("result2");
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                comment = collegeData.getString("comment");
                list.add(comment);

              /*  JSONObject collegeData2 = result2.getJSONObject(i);
                price = collegeData2.getString("test");
                list2.add(price);*/
            }

        } catch (JSONException e) {e.printStackTrace();}
    }








    /*public void sentData(String value){ //methos ใหม่ล่าสุด อัพเดทข้อมูลขึ้นฐานข้อมูล
        StrictMode.enableDefaults(); //จำกัดเวอร์เวอร์ชั่นแอนดรอยด์ที่ใช้ฟีเจอร์นี้ได้
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try{
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("isAdd","true")); //ข้อมูลที่จะส่งไป ตัวนี้เป็นแค่ตัวจุดชนวน
            nameValuePairs.add(new BasicNameValuePair("comment", value)); //ข้อมูลที่จะส่งไป ตัวนี้เป็นข้อมูลที่เราเขียน
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL + SENT_URL);//Change IP to you WebServer
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            httpclient.execute(httppost);

            Toast.makeText(main, "Completed.", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(main, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }*/



}
