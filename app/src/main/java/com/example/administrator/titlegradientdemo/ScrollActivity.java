package com.example.administrator.titlegradientdemo;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.titlegradientdemo.view.BannerLayout;
import com.example.administrator.titlegradientdemo.view.MyScrollview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 * 可能有点小bug
 */

public class ScrollActivity extends BaseActivity {
    private LinearLayout ll_titlebar;//标题兰
    private TextView tv_title;//标题
    private BannerLayout banner_layout;
    private List<Integer> mBanberDatas;
    private MyScrollview my_scrollView;

    @Override
    protected void initVariables() {
        mBanberDatas=new ArrayList<Integer>();
        mBanberDatas.add(0,R.drawable.banner1);
        mBanberDatas.add(1,R.drawable.banner3);
        mBanberDatas.add(2,R.drawable.banner4);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_scroll);
        my_scrollView = (MyScrollview) findViewById(R.id.my_scrollView);
        ll_titlebar = (LinearLayout) findViewById(R.id.ll_titlebar);
        //这块开始还是要设置一下背景透明度的，要不有时候会随机的显示
        ll_titlebar.getBackground().setAlpha(0);
        tv_title = (TextView) findViewById(R.id.tv_title);
        banner_layout = (BannerLayout) findViewById(R.id.banner_layout);
        if (null!=mBanberDatas&&!mBanberDatas.isEmpty())
        {
            banner_layout.setViewRes(mBanberDatas);
        }
    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        // 实现自定义控件中的监听器
       my_scrollView.setScrollViewListener(new MyScrollview.ScrollViewListener() {
           @Override
           public void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy) {
               titleAnima(y);
           }
       });
    }

    @Override
    protected void loadData() {

    }
    /**
     * 出现渐变效果
     */
    public void titleAnima(int y)
    {
        int height = banner_layout.getMeasuredHeight()/2;
        if (y >= 0 && y <= height)
        {
            float scrollPercent = (float) y / height;
            ll_titlebar.getBackground().setAlpha((int) (255 * scrollPercent));
            tv_title.setTextColor(Color.argb((int) scrollPercent * 255, 255, 255, 255));
        }
        else
        {
            //这两句代码不写title可能消失或随机出现，titleBar可能透明度随机。
            ll_titlebar.getBackground().setAlpha((int) (255 * 1));
            tv_title.setTextColor(Color.argb((int) 255, 255, 255, 255));
        }
    }
}
