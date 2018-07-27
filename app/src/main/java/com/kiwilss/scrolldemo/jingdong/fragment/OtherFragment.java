package com.kiwilss.scrolldemo.jingdong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.jingdong.adapter.OtherFgAdapter;
import com.kiwilss.scrolldemo.jingdong.widget.ItemListView;

import java.util.ArrayList;

/**
 * FileName: OtherFragment
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/27
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class OtherFragment extends Fragment {
    private ArrayList<Integer> mData;
    private com.kiwilss.scrolldemo.jingdong.widget.ItemListView ilv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fg_other, container, false);
        this.ilv = (ItemListView) rootView.findViewById(R.id.ilv);

        mData = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            mData.add(i);
        }
        OtherFgAdapter otherFgAdapter = new OtherFgAdapter(getContext(), mData);
        ilv.setAdapter(otherFgAdapter);

        return rootView;
    }
}
