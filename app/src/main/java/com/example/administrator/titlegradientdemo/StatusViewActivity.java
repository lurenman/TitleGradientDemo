package com.example.administrator.titlegradientdemo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/29.
 * 参考https://github.com/yanzhenjie/StatusView
 */

public class StatusViewActivity extends BaseActivity {


    private TextView tv_common;//渐变色状态栏
    private TextView tv_invasion;//内容侵入状态栏渐变
    private TextView tv_dark;//状态栏深色字体

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_status);
        tv_common = (TextView) findViewById(R.id.tv_common);
        tv_invasion = (TextView) findViewById(R.id.tv_invasion);
        tv_dark = (TextView) findViewById(R.id.tv_dark);
    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        tv_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StatusViewActivity.this,CommonActivity.class);
                startActivity(intent);
            }
        });
        tv_invasion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StatusViewActivity.this,DarkFontStatusActivity.class);
                startActivity(intent);
            }
        });
        tv_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StatusViewActivity.this,GoodsDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
