package com.example.uchih.distartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class list_cart_test extends AppCompatActivity {
    private  int num = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cart_test);

        final TextView txt = (TextView)findViewById(R.id.edit_num);
        final Button add = (Button)findViewById(R.id.add_cart);
        final Button delete = (Button)findViewById(R.id.delete_cart);



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
    }
}
