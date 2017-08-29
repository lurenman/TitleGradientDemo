/*
 * Copyright 2017 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.administrator.titlegradientdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yanzhenjie.statusview.NavigationView;
import com.yanzhenjie.statusview.StatusUtils;
import com.yanzhenjie.statusview.StatusView;

/**
 * <p>
 * Gradient status bar.
 * </p>
 * Created by YanZhenjie on 2017/8/2.
 * StatusView：用来替代系统的Status，开发者只需要把它布局在Layout的最顶部，控制它的background属性即可，其它都会自动处理。
 * NavigationView：用来替代系统的虚拟Navigationi，开发者只需要把它布局在Layout的最底部，控制它的backgroud属性即可，其它都会自动处理。
 * StatusUtils：用来设置Activity的状态栏和导航栏，这个类的方法要着重说明。
 * setStatusBarColor：设置状态栏颜色。
 * setNavigationBarColor：设置导航栏颜色。
 * setSystemBarColor：同时设置状态栏、导航栏颜色，每个颜色可以单独指定。
 * setFullToStatusBar：设置Activity的ContentView侵入到状态栏，并让状态栏透明，但是不会隐藏状态栏。
 * setFullToNavigationBar：设置Activity的ContentView侵入到虚拟导航栏，并让导航栏透明，但是不会隐藏导航栏。
 * setStatusBarDarkFont：设置状态栏的字体为深色，一般用于当Toolbar和状态栏为浅色时（比如白色状态栏）
 */
public class CommonActivity extends AppCompatActivity {

    NestedScrollView mNestedScrollView;

    StatusView mStatusView;
    Toolbar mToolbar;
    NavigationView mNavigationView;

    View mHeaderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        StatusUtils.setFullToStatusBar(this);  // StatusBar.
        StatusUtils.setFullToNavigationBar(this); // NavigationBar.

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mStatusView = (StatusView) findViewById(R.id.status_view);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        mNestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        mHeaderView = findViewById(R.id.header);

        final int startColor = ContextCompat.getColor(this, R.color.colorPrimary);
        final int endColor = ContextCompat.getColor(this, R.color.colorAccent);

        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int headerHeight = mHeaderView.getHeight();
                int scrollDistance = Math.min(scrollY, headerHeight);
                float fraction = (float) scrollDistance / (float) headerHeight;

                setToolbarStatusBarAlpha(evaluate(fraction, startColor, endColor));
                setNavigationViewColor(evaluate(fraction, endColor, startColor));
            }
        });
    }

    private void setToolbarStatusBarAlpha(int color) {
        DrawableCompat.setTint(mToolbar.getBackground().mutate(), color);
        DrawableCompat.setTint(mStatusView.getBackground().mutate(), color);
    }

    private void setNavigationViewColor(int color) {
        DrawableCompat.setTint(mNavigationView.getBackground().mutate(), color);
    }

    public int evaluate(float fraction, int startValue, int endValue) {
        int startA = (startValue >> 24) & 0xff;
        int startR = (startValue >> 16) & 0xff;
        int startG = (startValue >> 8) & 0xff;
        int startB = startValue & 0xff;

        int endA = (endValue >> 24) & 0xff;
        int endR = (endValue >> 16) & 0xff;
        int endG = (endValue >> 8) & 0xff;
        int endB = endValue & 0xff;

        return ((startA + (int) (fraction * (endA - startA))) << 24) |
                ((startR + (int) (fraction * (endR - startR))) << 16) |
                ((startG + (int) (fraction * (endG - startG))) << 8) |
                ((startB + (int) (fraction * (endB - startB))));
    }
}
