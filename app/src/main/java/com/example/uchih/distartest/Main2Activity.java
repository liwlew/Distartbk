package com.example.uchih.distartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private EditText addBox;
    private Button addButt;
    private MySQLConnect mySQLCon;
    private ListView dataListView;
    private List<String> items;
    private ArrayAdapter<String> adt; ///เพิ่มเข้ามาใหม่ล่าสุด

    public void update(){
        items = mySQLCon.getData();
        adt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items); //เพิ่มเข้ามาใหม่ล่าสุด
        dataListView.setAdapter(adt); //ทดแทนกันโดยเรียกใช้ adt แทน
    }

    public void init(){
        addBox = (EditText)findViewById(R.id.addBox);
        addButt = (Button)findViewById(R.id.addButt);
        dataListView = (ListView)findViewById(R.id.dataView);
        mySQLCon = new MySQLConnect(Main2Activity.this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}
