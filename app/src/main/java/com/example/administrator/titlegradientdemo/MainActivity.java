package com.example.administrator.titlegradientdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private TextView tv_listView;
    private TextView tv_recycleView;
    private TextView tv_scrollView;
    private TextView tv_statusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();

    }
    private void initViews() {
        tv_listView = (TextView) findViewById(R.id.tv_listView);
        tv_recycleView = (TextView) findViewById(R.id.tv_recycleView);
        tv_scrollView = (TextView) findViewById(R.id.tv_scrollView);
        tv_statusView = (TextView) findViewById(R.id.tv_statusView);
    }
    private void initEvents() {
        tv_listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListViewActivity.class);
                startActivity(intent);

            }
        });
        tv_recycleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RecycleActivity.class);
                startActivity(intent);
            }
        });
        tv_scrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ScrollActivity.class);
                startActivity(intent);

            }
        });
        tv_statusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,StatusViewActivity.class);
                startActivity(intent);
            }
        });
    }


}
