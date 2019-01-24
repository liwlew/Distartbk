package com.example.uchih.distartest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


public class webview extends AppCompatActivity {
    Handler handler=new Handler();
    String webUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);





   /*    try {
            WebView webview = (WebView) findViewById(R.id.web_pay);
            webview.setWebChromeClient(new WebChromeClient());
             webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            String url = "https://www.thaiepay.com/epaylink/payment.aspx";
         //   Log.d("cart","testbyliw---- "+   datamysql.price_c);

            String postData = "merchantid=" + URLEncoder.encode("39914985", "UTF-8") + "&redirecturl=" + URLEncoder.encode("thaiepay", "UTF-8")
                    + "&refno=" + URLEncoder.encode("099", "UTF-8")  + "&postbackurl=" + URLEncoder.encode("gg.gg", "UTF-8")
                    + "&returnurl=" + URLEncoder.encode("http://seoprojectmarketings.com/android/paysolutions.php", "UTF-8") + "&customeremail=" + URLEncoder.encode( "gg@gmail.com", "UTF-8")
                    + "&productdetail=" + URLEncoder.encode("testproduct", "UTF-8")+ "&total=" + URLEncoder.encode(  datamysql.price_c+"0", "UTF-8");
            webview.postUrl(url,postData.getBytes());

        }catch (UnsupportedEncodingException e){
            Log.e("Yourapp", "UnsupportedEncodingException");
          //  Log.d("Memory exceptions","exceptions"+e);
        }


        handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                WebView webview = (WebView) findViewById(R.id.web_pay);
                 webUrl = webview.getUrl();
                Toast.makeText(webview.this, webUrl, Toast.LENGTH_SHORT).show();
                if(webUrl.equals("http://seoprojectmarketings.com/android/paysolutions.php/")){
                    datamysql.list_number.clear();
                    datamysql.list_cart_name.clear();
                    datamysql.list_cart_price.clear();
                    datamysql.list_cart_image.clear();
                    datamysql.list_cart_proid.clear();
                    Intent i = new Intent(webview.this,thaigifts.class);
                    startActivity(i);
                }
               // handler.postDelayed(this, 5000);
            }
        };
       handler.postDelayed(r, 7000);*/


           WebView webview = (WebView) findViewById(R.id.web_pay);
        webview.setWebChromeClient(new WebChromeClient());
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://seoprojectmarketings.com/android/Ads_Streaming.php");




       // webview.loadUrl("http://seoprojectmarketings.com/android/vdo/Cyanide.mp4");

       // urlvdo = "http://seoprojectmarketings.com/android/vdo/Cyanide.mp4" ;
     /*   handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                WebView webview = (WebView) findViewById(R.id.web_pay);
                webUrl = webview.getUrl();
                Toast.makeText(webview.this, webUrl, Toast.LENGTH_SHORT).show();
                if(webUrl.equals("http://seoprojectmarketings.com/android/next_page.php")){

                    Intent i = new Intent(webview.this,thaigifts.class);
                    startActivity(i);
                }
                // handler.postDelayed(this, 5000);
            }
        };

        handler.postDelayed(r, 7000);*/
    }







}
