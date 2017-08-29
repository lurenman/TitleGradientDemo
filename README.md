# TitleGradientDemo
android 标题栏随着listview，scroolView，recycleView滑动，透明度随之变化。
# ListView关键代码
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
# Scroll关键代码
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
 # RecycleView关键代码
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
