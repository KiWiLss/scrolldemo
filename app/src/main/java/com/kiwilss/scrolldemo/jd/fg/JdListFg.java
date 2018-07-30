package com.kiwilss.scrolldemo.jd.fg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.jingdong.adapter.OtherFgAdapter;

import java.util.ArrayList;

/**
 * FileName: JdListFg
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/30
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class JdListFg extends Fragment {
    private ArrayList<Integer> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fg_test_list, container, false);

        ListView ilv = rootView.findViewById(R.id.lv);

        mData = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            mData.add(i);
        }
        OtherFgAdapter otherFgAdapter = new OtherFgAdapter(getContext(), mData);
        ilv.setAdapter(otherFgAdapter);

        return rootView;
    }
}
