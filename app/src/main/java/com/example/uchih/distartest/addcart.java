package com.example.uchih.distartest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class addcart extends AppCompatActivity {
    private  int num= 1;
    private ListView listView_cart;
    private ListView listView_cart2;
    private LinearLayout rela_price;
    private  Double total=0.00;
    private  int check_bank =0;
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
        Button submit = (Button)findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(SaveData()){

                   Intent i = new Intent(addcart.this,webview.class);
                   startActivity(i);
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
    }
    public  void totalitem(){
        for(int i = 0;i<datamysql.list_cart_price.size();i++){
             total= total + (Double.parseDouble( datamysql.list_cart_price.get(i))*datamysql.list_number.get(i));
        }
        TextView txt_total = (TextView)findViewById(R.id.txttotal);
        txt_total.setText(total+"0  TBH");
    }
    public  void onclicktotal(){
        Button total = (Button)findViewById(R.id.btn_totalsm);
        final RadioButton alipay =(RadioButton)findViewById(R.id.rdo_alipay);
        final RadioButton paysolution =(RadioButton)findViewById(R.id.rdo_paysolution);
        final RadioButton wechat =(RadioButton)findViewById(R.id.rdo_wechat);
        final LinearLayout view1 = (LinearLayout)findViewById(R.id.view1);
        final LinearLayout view2 =(LinearLayout)findViewById(R.id.view2);
        final RadioButton rdo_group =(RadioButton)findViewById(R.id.rdo_group);

        total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(rdo_group.isChecked()){
                    Toast.makeText(getApplicationContext(), "test_rdo", Toast.LENGTH_SHORT).show();
                }
                if(alipay.isChecked()){

                    check_bank= 2;
                    //  view1.setVisibility(View.GONE);
                }else if(paysolution.isChecked()){
                    check_bank= 3;
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                }else  if(wechat.isChecked()){
                    check_bank= 1;
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
            total= total + (Double.parseDouble( datamysql.list_cart_price.get(i))*datamysql.list_number.get(i));
        }

        TextView txt_total = (TextView)findViewById(R.id.txttotal);
        txt_total.setText(total+"0  TBH");
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
                Log.d("liwlew","itemtest----"+datamysql.list_cart_image.size());
//**************************************set add delete
                final TextView txt = (TextView) convertview.findViewById(R.id.edit_num);
                final Button add = (Button)convertview.findViewById(R.id.add_cart);
                final Button delete = (Button)convertview.findViewById(R.id.delete_cart);
                Button cancel =(Button) convertview.findViewById(R.id.cancel_cart);
                txt.setText(datamysql.list_number.get(i).toString());
                rela_price = (LinearLayout)findViewById(R.id.rela_price);
                rela_price.setVisibility(View.VISIBLE);
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
                        datamysql.list_number.remove(i);
                        datamysql.list_cart_name.remove(i);
                        datamysql.list_cart_price.remove(i);
                        datamysql.list_cart_image.remove(i);
                        datamysql.list_cart_proid.remove(i);
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
            String url = "http://pordeeshops.com/image/"+datamysql.list_cart_image.get(i);
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
        datamysql.name_c= txtname.getText().toString();
        datamysql.price_c= txtprice+"0";
        datamysql.email_c= txtemail.getText().toString();
        datamysql.location_c= txtlocation.getText().toString();
        datamysql.tel_c= txttel.getText().toString();
        // Dialog
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Error! ");
        ad.setIcon(android.R.drawable.btn_star_big_on);
        ad.setPositiveButton("Close", null);
        // Check Name
        if(txtname.getText().length() == 0)
        {
            ad.setMessage("Please input [Name] ");
            ad.show();
            txtname.requestFocus();
            return false;
        }
        // Check Email
        if(txtemail.getText().length() == 0)
        {
            ad.setMessage("Please input [Email] ");
            ad.show();
            txtemail.requestFocus();
            return false;
        }
        // Check Tel
        if(txttel.getText().length() == 0)
        {
            ad.setMessage("Please input [Tel] ");
            ad.show();
            txttel.requestFocus();
            return false;
        }
        if(txtlocation.getText().length() == 0)
        {
            ad.setMessage("Please input [location] ");
            ad.show();
            txtlocation.requestFocus();
            return false;
        }
        String url = "http://seoprojectmarketings.com/android/paysolution_android.php";
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("txtname", txtname.getText().toString()));
        params.add(new BasicNameValuePair("txtemail", txtemail.getText().toString()));
        params.add(new BasicNameValuePair("txttel", txttel.getText().toString()));
        params.add(new BasicNameValuePair("txtlocation", txtlocation.getText().toString()));
        params.add(new BasicNameValuePair("txtprice", txtprice));

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
            strError = c.getString("Error ");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Prepare Save Data
        if(strStatusID.equals("0"))
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


}
