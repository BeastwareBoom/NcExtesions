package com.vincyan.ncextensions.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xws on 2016/11/30.
 */

public class MyRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_FOOT = 0x01;
    private static final int TYPE_NORMAL = 0x02;
    private List<T> mDatas;
    private Context context;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_NORMAL) {
//            holder = new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item, parent, false));
        } else if (viewType == TYPE_FOOT) {
            //底部加载布局
//            holder = new FootViewHolder(LayoutInflater.from(context).inflate(R.layout.itemfooter, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    /*这里要注意的一点就是：我们占用了最底部的item所以在重写getItemCount方法时，要添加上一条数据，否则数据不对等*/

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOT;
        } else {
            return TYPE_NORMAL;
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        ItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder {

        FootViewHolder(View itemView) {
            super(itemView);
        }
    }

}