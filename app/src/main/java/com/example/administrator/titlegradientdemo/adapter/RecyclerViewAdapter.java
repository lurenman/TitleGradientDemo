package com.example.administrator.titlegradientdemo.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.titlegradientdemo.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mDatas;

    public RecyclerViewAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        mDatas=list;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
       // View view = holder.mView;
        holder.tv_Title.setText(mDatas.get(position));


    }

    @Override
    public int getItemCount() {
        return null==mDatas?0:mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView tv_Title;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_Title = (TextView) mView.findViewById(R.id.tv_Title);
        }
    }
}
