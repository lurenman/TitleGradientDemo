package com.example.administrator.titlegradientdemo;

import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.administrator.titlegradientdemo.adapter.ListViewAdapter;
import com.example.administrator.titlegradientdemo.view.BannerLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 * 参考文章
 * http://www.bubuko.com/infodetail-453161.html
 */

public class ListViewActivity extends BaseActivity {
    private ListView lv_listView;
    // ListView的头布局
    private View mHeaderView;
    // 头布局的高度
    private int headerHeight;
    private BannerLayout banner_layout;//头布局中的bannner
    private ListViewAdapter mListViewAdapter;
    private List<String> mDatas=new ArrayList<String>();
    private List<Integer> mBanberDatas;
    private LinearLayout ll_titlebar;

    @Override
    protected void initVariables() {
        for (int i = 0; i < 10; i++) {
            mDatas.add("item"+i);
        }
        mBanberDatas=new ArrayList<Integer>();
        mBanberDatas.add(0,R.drawable.banner1);
        mBanberDatas.add(1,R.drawable.banner3);
        mBanberDatas.add(2,R.drawable.banner4);
    }

    @Override
    protected void initViews() {
       setContentView(R.layout.activity_list);
        ll_titlebar = (LinearLayout) findViewById(R.id.ll_titlebar);
        lv_listView = (ListView) findViewById(R.id.lv_listView);
        mHeaderView=View.inflate(getApplicationContext(),R.layout.list_head,null);
        banner_layout=(BannerLayout) mHeaderView.findViewById(R.id.banner_layout);
        if (null!=mDatas&&!mDatas.isEmpty())
        mListViewAdapter=new ListViewAdapter(getApplicationContext(),mDatas);
        banner_layout.setViewRes(mBanberDatas);
        lv_listView.addHeaderView(mHeaderView);
        lv_listView.setAdapter(mListViewAdapter);

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        lv_listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            // 最重要的方法，标题栏的透明度变化在这个方法实现
            @Override
            public void onScroll(AbsListView listView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
// 判断当前最上面显示的是不是头布局，所以头布局的位置是0，即第一个（如果有刷新控件就位置加1）
                if (firstVisibleItem == 0) {
                    // 获取头布局
                    View view = lv_listView.getChildAt(0);
                    if (view != null) {
                        // 获取头布局现在的最上部的位置的相反数
                        int top = -view.getTop();
                        // 获取头布局的高度
                        headerHeight = view.getHeight();
                        // 满足这个条件的时候，是头布局在XListview的最上面第一个控件的时候，只有这个时候，我们才调整透明度
                        if (top <= headerHeight && top >= 0) {
                            // 获取当前位置占头布局高度的百分比
                            float f = (float) top / (float) headerHeight;
                            ll_titlebar.getBackground().setAlpha((int) (f * 255));
                            // 通知标题栏刷新显示
                            ll_titlebar.invalidate();
                        }
                    }
                } else if (firstVisibleItem > 0) {
                    ll_titlebar.getBackground().setAlpha(255);
                } else {
                    ll_titlebar.getBackground().setAlpha(0);
                }
            }

        });

    }

    @Override
    protected void loadData() {

    }
}
