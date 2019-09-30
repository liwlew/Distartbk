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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;


public class webview extends AppCompatActivity {
    Handler handler=new Handler();
    String webUrl;
    VideoView videoView;
    private   boolean stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);





       try {
            WebView webview = (WebView) findViewById(R.id.web_pay);
            webview.setWebChromeClient(new WebChromeClient());
             webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
            String url = "https://www.thaiepay.com/epaylink/payment.aspx";
         //   Log.d("cart","testbyliw---- "+   datamysql.price_c);
           double   total = 0.00;

           double number1 = Math.random();
           int num2 = new Random().nextInt(1000000000)  + 0;


           String ref = num2+"";
           for(int i = 0;i<datamysql.list_cart_price.size();i++){
                  total= total + (Double.parseDouble( datamysql.list_cart_price.get(i))*datamysql.list_number.get(i));
           }


            String postData = "merchantid=" + URLEncoder.encode("61311602", "UTF-8") + "&redirecturl=" + URLEncoder.encode("thaiepay", "UTF-8")
                    + "&refno=" + URLEncoder.encode(ref, "UTF-8")  + "&postbackurl=" + URLEncoder.encode("gg.gg", "UTF-8")
                    + "&returnurl=" + URLEncoder.encode("https://mannature.com/test/paysolutions.php", "UTF-8") + "&customeremail=" + URLEncoder.encode( "pordee@hotmail.com", "UTF-8")
                    + "&productdetail=" + URLEncoder.encode("testproduct", "UTF-8")+ "&total=" + URLEncoder.encode(   datamysql.price_c, "UTF-8");
            webview.postUrl(url,postData.getBytes());

        }catch (UnsupportedEncodingException e){
            Log.e("Yourapp", "UnsupportedEncodingException");
          //  Log.d("Memory exceptions","exceptions"+e);
        }

       stop= true;
        handler = new Handler();
         final Runnable r = new Runnable() {
            public void run() {
                WebView webview = (WebView) findViewById(R.id.web_pay);
                 webUrl = webview.getUrl();
              //  Toast.makeText(webview.this, webUrl, Toast.LENGTH_SHORT).show();


                if(webUrl.equals("https://mannature.com/test/paysolutions.php")&&stop){

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

                  /*  datamysql.list_number.clear();
                    datamysql.list_cart_name.clear();
                    datamysql.list_cart_price.clear();
                    datamysql.list_cart_image.clear();
                    datamysql.list_cart_proid.clear();*/
                    Intent i = new Intent(webview.this,thaigifts.class);
                    startActivity(i);
                    stop = false;

                }
                handler.postDelayed(this, 5000);
            }
        };

       handler.postDelayed(r, 7000);


         /* WebView webview = (WebView) findViewById(R.id.web_pay);
            webview.setWebChromeClient(new WebChromeClient());
             webview.setWebViewClient(new WebViewClient());
            webview.getSettings().setJavaScriptEnabled(true);
          //  String url = "http://seoprojectmarketings.com/android/paysolutions_post.php";
        String url = "http://seoprojectmarketings.com/android/main_page.php";

        webview.loadUrl(url);
       // webview.loadUrl("http://seoprojectmarketings.com/android/vdo/Cyanide.mp4");

       // urlvdo = "http://seoprojectmarketings.com/android/vdo/Cyanide.mp4" ;
        handler = new Handler();

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
       // test_2();


    }


    private void test_2(){
       /* String httpLiveUrl = "https://player.twitch.tv/?volume=0.5&!muted&channel=summit1g";
        videoView = (VideoView) findViewById(R.id.VideoView);
        videoView.setVideoURI(Uri.parse(httpLiveUrl));
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.requestFocus();
        videoView.start();*/



    }

private void youtube(){

    String frameVideo = "<html><body><br><iframe width=\"1280\" height=\"800\" src=\"https://www.youtube.com/embed/GVC5adzPpiE\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

    WebView displayYoutubeVideo = (WebView) findViewById(R.id.web_pay);
    displayYoutubeVideo.setWebViewClient(new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    });
    WebSettings webSettings = displayYoutubeVideo.getSettings();
    webSettings.setJavaScriptEnabled(true);
    displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
}


}
