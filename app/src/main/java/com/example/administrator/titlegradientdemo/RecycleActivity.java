package com.example.administrator.titlegradientdemo;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.titlegradientdemo.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 * 参考文章
 * http://blog.csdn.net/zsr0526/article/details/65947312
 */

public class RecycleActivity extends BaseActivity {
    private RecyclerView recycler_view;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private LinearLayout ll_titlebar;
    private TextView tv_Title;
    private List<String> mDatas = new ArrayList<String>();
    private int mDistance = 0;
    private int maxDistance = 255;//当距离在[0,255]变化时，透明度在[0,255之间变化]

    @Override
    protected void initVariables() {
        for (int i = 0; i < 15; i++) {
            mDatas.add("item" + i);
        }
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_recycle);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        ll_titlebar = (LinearLayout) findViewById(R.id.ll_titlebar);
        setSystemBarAlpha(0);
        tv_Title = (TextView) findViewById(R.id.tv_Title);
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerViewAdapter = new RecyclerViewAdapter(mContext, mDatas);
        recycler_view.setAdapter(mRecyclerViewAdapter);

    }


    @Override
    protected void initEnvent() {
        super.initEnvent();
         recycler_view.setOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);
             }
            //这块也可以根据控件高度算出百分比的那种
             @Override
             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                 super.onScrolled(recyclerView, dx, dy);
                 //dy:每一次竖直滑动增量 向下为正 向上为负
                 mDistance += dy;
                 float percent = mDistance * 1f / maxDistance;//百分比
                 int alpha = (int) (percent * 255);
                 //int argb = Color.argb(alpha, 57, 174, 255);
                 setSystemBarAlpha(alpha);
             }
         });
    }

    @Override
    protected void loadData() {

    }

    /**
     * 设置标题栏背景透明度
     *
     * @param alpha 透明度
     */
    private void setSystemBarAlpha(int alpha) {
        if (alpha >= 255) {
            alpha = 255;//设置透明度最大为不透明
        } else {
            //标题栏渐变。a:alpha透明度 r:红 g：绿 b蓝
//        ll_titlebar.setBackgroundColor(Color.rgb(57, 174, 255));//没有透明效果
//        ll_titlebar.setBackgroundColor(Color.argb(alpha, 57, 174, 255));//透明效果是由参数1决定的，透明范围[0,255]
            ll_titlebar.getBackground().setAlpha(alpha);
        }
    }
}
