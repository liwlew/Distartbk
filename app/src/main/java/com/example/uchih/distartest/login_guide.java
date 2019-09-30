package com.example.uchih.distartest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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
    private String status = "";
    private String idcar = "";
    private static final String MY_PREFS = "";
    private String stringValue ="";
    private  boolean check = false;
    private  String pmk ="";
    private String url = "https://mannature.com/test/checkin.php";
    private String url2 = "https://mannature.com/test/register.php";
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guide);

        //--------------------------------------------------------------------------

        // Get SharedPreferences
        shared= getSharedPreferences(MY_PREFS,
                Context.MODE_PRIVATE);
        // หรือ
        SharedPreferences shared2 = getApplicationContext().getSharedPreferences(MY_PREFS,
                Context.MODE_PRIVATE);

        // Save SharedPreferences
       /* SharedPreferences.Editor editor = shared.edit();
        editor.putString("stringKey", "tset");
        editor.putBoolean("booleanKey", true);
        editor.commit();*/


         stringValue = shared.getString("stringKey", "not found!");
        boolean booleanValue = shared.getBoolean("booleanKey", false);

//        Log.i("LOG_TAG", "String value: " + stringValue);
//        Log.i("LOG_TAG ", "Boolean value: " + booleanValue);
//----------------------------------------------------------------------
        final Button btn_back = (Button) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(login_guide.this,thaigifts.class);
                startActivity(i);
            }
        });
        check();

        // btnhome
        final Button btn__home_admin = (Button) findViewById(R.id.btn__home_admin);
        final Button btn__home_cus = (Button) findViewById(R.id.btn__home_cus);
        final Button btn__home_idcar = (Button) findViewById(R.id.btn__home_idcar);
        final Button btn__home_menu = (Button) findViewById(R.id.btn__home_menu);
        btn__home_admin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(login_guide.this,thaigifts.class);
                startActivity(i);
            }
        });
        btn__home_cus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(login_guide.this,thaigifts.class);
                startActivity(i);
            }
        });
        btn__home_admin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(login_guide.this,thaigifts.class);
                startActivity(i);
            }
        });

        btn__home_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(login_guide.this,thaigifts.class);
                startActivity(i);
            }
        });
        btn__home_idcar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(login_guide.this,thaigifts.class);
                startActivity(i);
            }
        });
        // btn
        final Button btnLogin = (Button) findViewById(R.id.btnlogin);
        final Button btn_regiser = (Button) findViewById(R.id.btn_regiser);
        final Button btn_idcar = (Button) findViewById(R.id.btn_idcar);
        final Button btn_sub = (Button) findViewById(R.id.btn_sub);

        // layout
        final RelativeLayout customer = (RelativeLayout)findViewById(R.id.layout_customer);
        final RelativeLayout layout_admin = (RelativeLayout)findViewById(R.id.layout_admin);
        final RelativeLayout lg_car = (RelativeLayout)findViewById(R.id.layout_login_car);
        final RelativeLayout menu = (RelativeLayout)findViewById(R.id.layout_manu);
        final LinearLayout login = (LinearLayout)findViewById(R.id.layout_login);
        final RelativeLayout layout_idcar = (RelativeLayout)findViewById(R.id.layout_idcar);


        // btnback
        final Button btn_back_admin = (Button) findViewById(R.id.btn_back_admin);
        final Button btn_back_cus = (Button) findViewById(R.id.btn_back_cus);
        final Button btn_back_lgidcar = (Button) findViewById(R.id.btn_back_lgidcar);
        final Button btn_back_idcar = (Button) findViewById(R.id.btn_back_idcar);
        final Button btn_back_menu = (Button) findViewById(R.id.btn_back_menu);
        btn_back_admin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                layout_admin.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
            }
        });
        btn_back_cus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login.setVisibility(View.VISIBLE);
                customer.setVisibility(View.GONE);
            }
        });

        btn_back_lgidcar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                menu.setVisibility(View.VISIBLE);
                lg_car.setVisibility(View.GONE);
            }
        });
        btn_back_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login.setVisibility(View.VISIBLE);
                menu.setVisibility(View.GONE);
            }
        });
        btn_back_idcar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lg_car.setVisibility(View.VISIBLE);
                layout_idcar.setVisibility(View.GONE);
            }
        });
        //edttext
       final EditText edt_id = (EditText)findViewById(R.id.edt_id);
       final EditText txtUser = (EditText)findViewById(R.id.edtxt1);
        final EditText txtPass = (EditText)findViewById(R.id.edtxt2);
        // Perform action on click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(txtUser.getText().toString().equals("admin") && txtPass.getText().toString().equals("1234")){
                    login.setVisibility(View.GONE);
                    menu.setVisibility(View.VISIBLE);
                   // Toast.makeText(login_guide.this, "Login Success "+"\n"+"Hi admin ", Toast.LENGTH_LONG).show();
                 // Toast.makeText(login_guide.this,  stringValue, Toast.LENGTH_LONG).show();
                }else{

                    downloadJSON("https://mannature.com/test/guide.php");
                }




            }

        });


         Button btn_regis = (Button)findViewById(R.id.btn_register);
        btn_regis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                register();
            }
        });
        txtPass.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    EditText user =(EditText)findViewById(R.id.edtxt1);
                    EditText pass =(EditText)findViewById(R.id.edtxt2);
                    if(txtUser.getText().toString().equals("admin") && txtPass.getText().toString().equals("1234")){
                        login.setVisibility(View.GONE);
                        menu.setVisibility(View.VISIBLE);
                        txtUser.setText("");
                        txtPass.setText("");
//                        Toast.makeText(login_guide.this, "Login Success "+"\n"+"Hi admin ", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(login_guide.this,  stringValue, Toast.LENGTH_LONG).show();
                    }else{
                        downloadJSON("https://mannature.com/test/guide.php");

                    }

                    return true;
                }
                return false;
            }
        });
        //----------------------------------menu--------------------------
        btn_regiser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                layout_admin.setVisibility(View.VISIBLE);
                menu.setVisibility(View.GONE);
            }
        });
        btn_idcar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lg_car.setVisibility(View.VISIBLE);
                menu.setVisibility(View.GONE);
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(edt_id.getText().toString().equals("1150")){
                    layout_idcar.setVisibility(View.VISIBLE);
                    lg_car.setVisibility(View.GONE);
                    edt_id.setText("");
                }

            }
        });
        edt_id.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    if(edt_id.getText().toString().equals("1150")){
                        layout_idcar.setVisibility(View.VISIBLE);
                        lg_car.setVisibility(View.GONE);
                        edt_id.setText("");
                    }
                    return true;
                }
                return false;
            }
        });
        //set carid

        TextView  btn_go_login = (TextView)findViewById(R.id.btn_go_login);
        btn_go_login.setText(stringValue);
        Button  btn_chage_idcar  =(Button)  findViewById(R.id.btn_chage_idcar);
        btn_chage_idcar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText edt_idcar = (EditText)findViewById(R.id.edt_idcar);

                TextView  btn_go_login = (TextView)findViewById(R.id.btn_go_login);

                btn_go_login.setText(edt_idcar.getText().toString());

                SharedPreferences.Editor editor = shared.edit();
                editor.putString("stringKey", edt_idcar.getText().toString());
                editor.putBoolean("booleanKey", true);
                editor.commit();
                stringValue = shared.getString("stringKey", "not found!");

                Toast.makeText(login_guide.this,  stringValue, Toast.LENGTH_LONG).show();
            }
        });


        //click ative status
        final Button btn_checkin = (Button)findViewById(R.id.btn_checkin);
        final Button btn_checkout = (Button)findViewById(R.id.btn_checkout);
        final EditText edt_location = (EditText)findViewById(R.id.edt_location);
        btn_checkin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                edt_location.setEnabled(false);
                btn_checkin.setEnabled(false);
                btn_checkout.setEnabled(true);
                checkin();

            }
        });
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkout();
                edt_location.setText("");
                edt_location.setEnabled(true);
                btn_checkin.setEnabled(true);
                btn_checkout.setEnabled(false);
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
        final EditText edt_location = (EditText)findViewById(R.id.edt_location);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            if(txtUser.getText().toString().equals(obj.optString("id" )) && txtPass.getText().toString().equals(obj.optString("password" ))  ){
                name =  obj.optString("name" );
                status =  obj.optString("status" );
                idcar =  obj.optString("bus_id" );
                pmk = obj.optString("pmk" );
                Toast.makeText(login_guide.this, "Login Success "+"\n"+"Hi "+name, Toast.LENGTH_SHORT).show();
                setinvi();
                if(!obj.optString("location" ).equals("")){
                    edt_location.setEnabled(false);
                    edt_location.setText(obj.optString("location" ));
                }

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

    public  void setinvi(){
        EditText user =(EditText)findViewById(R.id.edtxt1);
        EditText pass =(EditText)findViewById(R.id.edtxt2);

        final RelativeLayout customer = (RelativeLayout)findViewById(R.id.layout_customer);
        final LinearLayout login = (LinearLayout)findViewById(R.id.layout_login);
        check();
        user.setText("");
        pass.setText("");
        login.setVisibility(View.GONE);
        customer.setVisibility(View.VISIBLE);


    }
    public  void check(){
        Button btn_checkin = (Button)findViewById(R.id.btn_checkin);
        Button btn_checkout = (Button)findViewById(R.id.btn_checkout);
        if(status.equals("y")&&idcar.equals(stringValue)){
            //updatedata
            btn_checkin.setEnabled(false);
            btn_checkout.setEnabled(true);
        }else{
            //updatedata
            btn_checkin.setEnabled(true);
            btn_checkout.setEnabled(false);
        }
    }


    public void checkout(){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("txtvalue", ""));
        params.add(new BasicNameValuePair("txtstatus", "n"));
        params.add(new BasicNameValuePair("txtpmk", pmk));
        params.add(new BasicNameValuePair("txtlocation", ""));
        try {
            JSONArray data = new JSONArray(getJSONUrl(url,params));


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void register(){

        EditText name = (EditText)findViewById(R.id.edit_name);
        EditText surname = (EditText)findViewById(R.id.edit_surname);
        EditText pass = (EditText)findViewById(R.id.edit_pass);
        EditText conpass = (EditText)findViewById(R.id.edit_conpass);
        EditText id = (EditText)findViewById(R.id.edit_user);
        EditText phone = (EditText)findViewById(R.id.edit_phone);

      //  EditText phone = (EditText)findViewById(R.id.edit_pass);
        if(!pass.getText().toString().equals(conpass.getText().toString())){
            Toast.makeText(login_guide.this, "password is not match ", Toast.LENGTH_SHORT).show();
        }else{
            String n = name.getText().toString()+" "+surname.getText().toString();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("txtid", id.getText().toString()));
            params.add(new BasicNameValuePair("txtname",n ));
            params.add(new BasicNameValuePair("txtpassword", pass.getText().toString()));
            params.add(new BasicNameValuePair("txtvalue", ""));
            params.add(new BasicNameValuePair("txtstatus", "n"));
            params.add(new BasicNameValuePair("txtphone", phone.getText().toString()));
            // params.add(new BasicNameValuePair("txtpmk", pmk));
            Toast.makeText(login_guide.this, "add Success ", Toast.LENGTH_SHORT).show();

            name.setText("");
            surname.setText("");
            pass.setText("");
            conpass.setText("");
            id.setText("");
            phone.setText("");


            try {
                JSONArray data = new JSONArray(getJSONUrl(url2,params));


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }




    }


    public void checkin()
    {
        // Paste Parameters
        EditText edt_location = (EditText)findViewById(R.id.edt_location);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("txtvalue", stringValue));
        params.add(new BasicNameValuePair("txtstatus", "y"));
        params.add(new BasicNameValuePair("txtlocation", edt_location.getText().toString()));
        params.add(new BasicNameValuePair("txtpmk", pmk));
        try {
            JSONArray data = new JSONArray(getJSONUrl(url,params));


            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);
            }


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
