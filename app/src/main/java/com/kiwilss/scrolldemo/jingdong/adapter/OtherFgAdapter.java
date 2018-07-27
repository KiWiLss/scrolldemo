package com.kiwilss.scrolldemo.jingdong.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kiwilss.scrolldemo.R;

import java.util.List;

/**
 * FileName: OtherFgAdapter
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/27
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class OtherFgAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Integer> mData;

    public OtherFgAdapter(Context context, List<Integer> data) {
        mContext = context;
        mData = data;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder")
        View rootView = mLayoutInflater.inflate(R.layout.item, viewGroup, false);


        return rootView;
    }
}
