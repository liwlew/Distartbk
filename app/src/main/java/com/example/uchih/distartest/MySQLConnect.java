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
    public String URL = "https://mannature.com", GET_URL = "/test/get_post.php?status=1", SENT_URL = "test/sent_post.php";
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
    public List<String> getData2() {    return list2; }

    private void showJSON(String response) {
        String comment = "";
        String price = "";
        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                if(collegeData.optString("comment")!=""){
                    comment = collegeData.optString("comment");
                    list.add(comment);
                }
                if(collegeData.optString("test")!=""){
                    price = collegeData.optString("test");
                    list2.add(price);
                }


            }

        } catch (JSONException e) {e.printStackTrace();}
    }











}
