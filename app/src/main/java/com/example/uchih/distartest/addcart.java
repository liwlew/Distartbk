package com.example.uchih.distartest;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class addcart extends AppCompatActivity {
    private  int num= 1;
    private ListView listView_cart;
    private ListView listView_cart2;
    private LinearLayout rela_price;
    private  Double total=0.00;
    private   String[]image_wechat;
    private VideoView vdoon;
    private  LinearLayout viewall;
    private   String urlvdo ="";
    private String bk = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcart);

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        //toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor((Color.parseColor("#4CAF50")));
        setSupportActionBar(toolbar);

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
//---------------------------------------------------------------------search

        final EditText editText_search = (EditText)findViewById(R.id.edit_txt_search);
        editText_search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    datamysql.Search = editText_search.getText().toString();
                    Intent i = new Intent(addcart.this,search.class);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
       // -------------------------------------------------------
        Button next2 = (Button)findViewById(R.id.button);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(addcart.this,thaigifts.class);
                startActivity(i);
            }
        });
        Button next3 = (Button)findViewById(R.id.button2);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(addcart.this,travel.class);
                startActivity(i);
            }
        });
        Button next4 = (Button)findViewById(R.id.button3);
        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(addcart.this,food.class);
                startActivity(i);
            }
        });
        Button next = (Button)findViewById(R.id.button4);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(addcart.this,hotel.class);
                startActivity(i);
            }
        });
        Button test = (Button)findViewById(R.id.btmtest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(addcart.this,login_guide.class);
                startActivity(i);
            }
        });
        onclicktotal();
        final RadioButton alipay =(RadioButton)findViewById(R.id.rdo_alipay);
        final RadioButton paysolution =(RadioButton)findViewById(R.id.rdo_paysolution);
        final RadioButton wechat =(RadioButton)findViewById(R.id.rdo_wechat);
        final LinearLayout view1 = (LinearLayout)findViewById(R.id.view1);
        final LinearLayout view2 =(LinearLayout)findViewById(R.id.view2);
        final LinearLayout view_aw =(LinearLayout)findViewById(R.id.view_aw);

        Button submit = (Button)findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SaveData()){
                    if( datamysql.check_bank==3){

                        Intent i = new Intent(addcart.this,webview.class);
                        startActivity(i);
                    }else if( datamysql.check_bank==1){
                        bk="wechat";
                        //   datamysql.check_bank= 1;
                        view_aw();
                        view_aw.setVisibility(View.VISIBLE);
                        view2.setVisibility(View.GONE);
                    }else if(datamysql.check_bank==2){
                        bk="alipay";
                        //   datamysql.check_bank= 2;
                        view_aw();
                        view_aw.setVisibility(View.VISIBLE);
                        view2.setVisibility(View.GONE);
                    }
                }

            }
        });
        listView_cart = (ListView)findViewById(R.id.listview_cart);
        listView_cart2 = (ListView)findViewById(R.id.listview_cart2);
        if(datamysql.arrayck==false) {
            listView_cart.setAdapter(new addcart.Efficientadapter(getApplicationContext()));
            listView_cart2.setAdapter(new addcart.Efficientadapter2(getApplicationContext()));
            totalitem();
        }

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
        num_cart();
        setlang();
        cancelBtm();
    }
    public  void cancelBtm(){
        Button cancel = (Button)findViewById(R.id.btm_cancel);
        final WebView webview = (WebView) findViewById(R.id.web_aw);
        final LinearLayout view_aw =(LinearLayout)findViewById(R.id.view_aw);
        final LinearLayout view1 = (LinearLayout)findViewById(R.id.view1);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(addcart.this,thaigifts.class);
                startActivity(i);
//                view_aw.setVisibility(View.GONE);
//                view1.setVisibility(View.VISIBLE);
            }
        });
    }
    public  void totalitem(){
        for(int i = 0;i<datamysql.list_cart_price.size();i++){
             total= total + (Double.parseDouble( datamysql.list_cart_price.get(i))*datamysql.list_number.get(i));
        }
        total=total+100.00;
        TextView txt_total = (TextView)findViewById(R.id.txttotal);
        TextView txt_delivery = (TextView)findViewById(R.id.txtdelively);
        txt_total.setText(total+"0  THB");
        txt_delivery.setText("100.00 THB");
        String txtprice = total.toString();
        datamysql.price_c= txtprice+"0";
    }
    public  void view_aw(){
      //  String parth = "http://kaaidee.com/android/image/download.png";
        ImageView image_aw = (ImageView)findViewById(R.id.image_aw);
       // Picasso.get().load(parth).into(image_aw);
       /* byte[] decodedString = Base64.decode(parth, Base64.DEFAULT);
        Bitmap bitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        image_aw.setImageBitmap(bitMap);*/
      /*  WebView webview = (WebView) findViewById(R.id.web_aw);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        String url = "http://seolnwza.com/wechat.php";
        webview.loadUrl(url);*/

        try {
            WebView webview = (WebView) findViewById(R.id.web_aw);
            webview.setWebChromeClient(new WebChromeClient());
            webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            String url = "https://mannature.com/test/payment/wechat.php";
//            Log.d("cart","testbyliw---- "+   datamysql.price_c);
            double p = Double.parseDouble(datamysql.price_c);
           int price  =  (int) p;

          // channel: wechat or alipay
            String postData = "txtbk=" + URLEncoder.encode(bk, "UTF-8") +"&txtprice=" + URLEncoder.encode(  price+"", "UTF-8");
            webview.postUrl(url,postData.getBytes());

        }catch (UnsupportedEncodingException e){
            Log.e("Yourapp", "UnsupportedEncodingException");
            //  Log.d("Memory exceptions","exceptions"+e);
        }
        for(int i=0;i<datamysql.list_cart_name.size();i++){
            if(datamysql.list_cart_check.get(i)){
                datamysql.list_number.remove(i);
                datamysql.list_cart_name.remove(i);
                datamysql.list_cart_price.remove(i);
                datamysql.list_cart_image.remove(i);
                datamysql.list_cart_proid.remove(i);
                datamysql.numcart_ = datamysql.numcart_ -  1;
            }
        }

    }
    public  void onclicktotal(){
        Button total = (Button)findViewById(R.id.btn_totalsm);
        final RadioButton alipay =(RadioButton)findViewById(R.id.rdo_alipay);
        final RadioButton paysolution =(RadioButton)findViewById(R.id.rdo_paysolution);
        final RadioButton wechat =(RadioButton)findViewById(R.id.rdo_wechat);
        final LinearLayout view1 = (LinearLayout)findViewById(R.id.view1);
        final LinearLayout view2 =(LinearLayout)findViewById(R.id.view2);
        final LinearLayout view_aw =(LinearLayout)findViewById(R.id.view_aw);

        final RadioButton rdo_group =(RadioButton)findViewById(R.id.rdo_group);
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Error! ");
        ad.setIcon(android.R.drawable.btn_star_big_on);
        ad.setPositiveButton("Close", null);
       // total.setEnabled(false);

        ImageView view_paysolution = (ImageView)findViewById(R.id.paysolution);
        ImageView view_alipay = (ImageView)findViewById(R.id.alipay);
        ImageView view_wechat = (ImageView)findViewById(R.id.wechat);

        view_paysolution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paysolution.setChecked(true);
            }
        });
        view_alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alipay.setChecked(true);

            }
        });
        view_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wechat.setChecked(true);
            }
        });

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(datamysql.numcart_==0){
                    ad.setMessage("Please Select Item ");
                    ad.show();
                }else {
                   if(alipay.isChecked()){
                       datamysql.check_bank= 2;
                     /*  bk="alipay";
                       view_aw();
                    view_aw.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.GONE);*/
                       view1.setVisibility(View.GONE);
                       view2.setVisibility(View.VISIBLE);

                }else if(paysolution.isChecked()){
                   datamysql.check_bank= 3;
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                }else  if(wechat.isChecked()){
                       datamysql.check_bank= 1;
                     /*  bk="wechat";
                       view_aw();
                    view_aw.setVisibility(View.VISIBLE);
                    view1.setVisibility(View.GONE);*/
                       view1.setVisibility(View.GONE);
                       view2.setVisibility(View.VISIBLE);

                }else{
                    ad.setMessage("Please Check Payment ");
                    ad.show();
                   }
                }
            }
        });
        Button btn_back = (Button)findViewById(R.id.back_alipay);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
            }
        });
    }
    public  void totaldelet(){
        total=0.0;
        for(int i = 0;i<datamysql.list_cart_price.size();i++){
            if(datamysql.list_cart_check.get(i)){
                total= total + (Double.parseDouble( datamysql.list_cart_price.get(i))*datamysql.list_number.get(i));
            }

        }
        total=total+100.00;

        TextView txt_total = (TextView)findViewById(R.id.txttotal);
        txt_total.setText(total+"0  THB");
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
            return datamysql.list_cart_name.size();
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
            addcart.Efficientadapter.ViewHolder holder = null;
            if(convertview ==null){
                convertview =mInflate.inflate(R.layout.activity_list_cart_test, null);
                holder = new addcart.Efficientadapter.ViewHolder();
           //     Log.d("liwlew","itemtest----"+datamysql.list_cart_image.size());
//**************************************set add delete
                final TextView txt = (TextView) convertview.findViewById(R.id.edit_num);
                final Button add = (Button)convertview.findViewById(R.id.add_cart);
                final Button delete = (Button)convertview.findViewById(R.id.delete_cart);
                CheckBox  checkbox_cart=(CheckBox) convertview.findViewById(R.id.checkbox_cart);
                Button cancel =(Button) convertview.findViewById(R.id.cancel_cart);
                txt.setText(datamysql.list_number.get(i).toString());
                rela_price = (LinearLayout)findViewById(R.id.rela_price);
                rela_price.setVisibility(View.VISIBLE);
                checkbox_cart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        datamysql.list_cart_check.set(i,isChecked);
                        totaldelet();
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tt = (String) txt.getText().toString();
                        num = Integer.parseInt(tt);
                        tt = ""+(num+1);
                        txt.setText(tt);
                        datamysql.list_number.set(i,Integer.parseInt(txt.getText().toString()));
                        totaldelet();
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
                        datamysql.list_number.set(i,Integer.parseInt(txt.getText().toString()));
                        totaldelet();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                  // datamysql.numcart_ = datamysql.numcart_ - datamysql.list_number.get(i) ;
                        datamysql.list_number.remove(i);
                        datamysql.list_cart_name.remove(i);
                        datamysql.list_cart_price.remove(i);
                        datamysql.list_cart_image.remove(i);
                        datamysql.list_cart_proid.remove(i);
                        datamysql.numcart_ = datamysql.numcart_ -1;
                                //  Log.d("cart","testbyliw---- "+i);
                       finish();
                        startActivity(getIntent());
                    }
                });
//***************************************************
                holder.authorImagel=(ImageView) convertview.findViewById(R.id.image_cart);
                holder.title=(TextView)convertview.findViewById(R.id.name_cart);
                holder.price_item=(TextView)convertview.findViewById(R.id.txt_item_price);
               // holder.numberitem=(TextView) convertview.findViewById(R.id.edit_num);
                convertview.setTag(holder);
            }else{
                //rebind widget
                holder = (addcart.Efficientadapter.ViewHolder)convertview.getTag();
            }
            holder.price_item.setText(datamysql.list_cart_price.get(i).substring(0,datamysql.list_cart_price.get(i).length()-2) + "  THB");
            holder.title.setText(datamysql.list_cart_name.get(i));
        //    holder.numberitem.setText(datamysql.list_number.get(i));
            //      String url = "http://pordeeshops.com/image/cache/catalog/product1/forman/Image_a71ecfc-1600x1600-200x200.jpg";
            String url = "https://kaaidee.com/image/"+datamysql.list_cart_image.get(i);
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
    //*********************************************************************************************customview
    public class Efficientadapter2 extends BaseAdapter {
        public Context mContext;
        public LayoutInflater mInflate;
        public Efficientadapter2(Context context){
            mContext = context;
            mInflate = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            return datamysql.list_cart_name.size();
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
            addcart.Efficientadapter2.ViewHolder holder = null;
            if(convertview ==null){
                convertview =mInflate.inflate(R.layout.list_price, null);
                holder = new addcart.Efficientadapter2.ViewHolder();
                holder.title=(TextView)convertview.findViewById(R.id.list_productn);
                holder.price_item=(TextView)convertview.findViewById(R.id.list_price);
                // holder.numberitem=(TextView) convertview.findViewById(R.id.edit_num);
                convertview.setTag(holder);
            }else{
                //rebind widget
                holder = (addcart.Efficientadapter2.ViewHolder)convertview.getTag();
            }
            holder.price_item.setText(datamysql.list_cart_price.get(i).substring(0,datamysql.list_cart_price.get(i).length()-2) + "  THB");
            holder.title.setText(datamysql.list_cart_name.get(i));

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
    public boolean SaveData()
    {
       final EditText txtname = (EditText)findViewById(R.id.edt_name);
        final EditText txtemail = (EditText)findViewById(R.id.edt_email);
        final EditText txttel = (EditText)findViewById(R.id.edt_call);
        final EditText txtlocation = (EditText)findViewById(R.id.edt_location);
        //final EditText txtprice = (EditText)findViewById(R.id.txttotal);
        String txtprice = total.toString();
        datamysql.price_c= txtprice+"0";
        datamysql.name_c= txtname.getText().toString();

        datamysql.email_c= txtemail.getText().toString();
        datamysql.location_c= txtlocation.getText().toString();
        datamysql.tel_c= txttel.getText().toString();
        // Dialog
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Error! ");
        ad.setIcon(android.R.drawable.btn_star_big_on);
        ad.setPositiveButton("Close", null);
        // Check Name

        // Check Email

//        if(txtemail.getText().length() == 0)
//        {
//            ad.setMessage("Please input [Email] ");
//            ad.show();
//            txtemail.requestFocus();
//            return false;
//        }
        // Check Tel

//        if(txttel.getText().length() == 0)
//        {
//            ad.setMessage("Please input [Tel] ");
//            ad.show();
//            txttel.requestFocus();
//            return false;
//        }
        txttel.setText("026292211");
        txtemail.setText("pordee@hotmail.com");
        if(txtname.getText().length() == 0)
        {
            ad.setMessage("Please input [Name] ");
            ad.show();
            txtname.requestFocus();
            return false;
        }
        if(txtlocation.getText().length() == 0)
        {
            ad.setMessage("Please input [location] ");
            ad.show();
            txtlocation.requestFocus();
            return false;
        }
        String id_pro=datamysql.list_cart_proid.get(0);
        for(int i =1;i<datamysql.list_cart_proid.size();i++){
             id_pro = id_pro +","+datamysql.list_cart_proid.get(i);
        }

        String url = "https://mannature.com/test/paysolution_android.php";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("txtname", txtname.getText().toString()));
        params.add(new BasicNameValuePair("txtemail", txtemail.getText().toString()));
        params.add(new BasicNameValuePair("txttel", txttel.getText().toString()));
        params.add(new BasicNameValuePair("txtlocation", txtlocation.getText().toString()));
        params.add(new BasicNameValuePair("txtprice", txtprice));
        params.add(new BasicNameValuePair("txtid", id_pro));
        params.add(new BasicNameValuePair("txtactive", "n"));
        /** Get result from Server (Return the JSON Code)
         * StatusID = ? [0=Failed,1=Complete]
         * Error	= ?	[On case error return custom error message]
         *
         * Eg Save Failed = {"StatusID":"0","Error":"Email Exists!"}
         * Eg Save Complete = {"StatusID":"1","Error":""}
         */

        String resultServer  = netconnect.getHttpPost(url,params);

        /*** Default Value ***/
        String strStatusID = "0";
        String strError = "Unknow Status! "+txtprice;

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strStatusID = c.getString("StatusID");
            strError = c.getString("Error");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Prepare Save Data
        if(strStatusID.equals("1"))
        {
            ad.setMessage(strError);
            ad.show();
        }
        else
        {
            Toast.makeText(addcart.this, "Save Data Successfully", Toast.LENGTH_SHORT).show();
            txtname.setText("");
            txtemail.setText("");
            txttel.setText("");
            txtlocation.setText("");

        }

        return true;
    }
//--------------------------------------------------

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

    public void wechatpay()
    {
        // listView1
        final ListView lisView1 = (ListView)findViewById(R.id.listView1);

        // editText1
        final EditText inputText = (EditText)findViewById(R.id.editText1);
        String url = "https://mannature.com/test/payment/wechatpay_android.php";

        // Paste Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        Log.d("Searchliw","itemtest----"+inputText.getText().toString());
        String seach = inputText.getText().toString().trim();

        params.add(new BasicNameValuePair("txtKeyword", inputText.getText().toString()));
        // params.add(new BasicNameValuePair("txtKeyword", "ออย"));
        try {
            JSONArray data = new JSONArray(netconnect.getHttpPost(url,params));

            final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            image_wechat = new String[data.length()];

            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                image_wechat[i] =  c.optString("image" );
            }

         //   griview.setAdapter(new addcart.Efficientadapter(getApplicationContext()));

            Dialog settingsDialog = new Dialog(this);
            settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.image_wechat_layout
                    , null));
            settingsDialog.show();

           /* final android.app.AlertDialog.Builder viewDetail = new android.app.AlertDialog.Builder(this);
            // OnClick Item
            lisView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> myAdapter, View myView,
                                        int position, long mylng) {

                }
            });*/


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
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
