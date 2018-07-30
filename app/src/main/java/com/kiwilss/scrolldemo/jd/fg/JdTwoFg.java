package com.kiwilss.scrolldemo.jd.fg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.jd.adapter.JdOneAdapter;
import com.kiwilss.scrolldemo.widget.NoScrollViewPager;

import java.util.ArrayList;

/**
 * FileName: JdTwoFg
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/30
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class JdTwoFg extends Fragment {
    private android.support.design.widget.TabLayout tljdtwotab;
    private com.kiwilss.scrolldemo.widget.NoScrollViewPager nsvjdtwovp;
    private ArrayList<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fg_jd_two, container, false);
        this.nsvjdtwovp = (NoScrollViewPager) rootView.findViewById(R.id.nsv_jd_two_vp);
        this.tljdtwotab = (TabLayout) rootView.findViewById(R.id.tl_jd_two_tab);

        //控制viewpager是否可以滑动
        //nsvjdtwovp.setNoScroll(true);

        mFragments = new ArrayList<>();
        JdWebFg jdWebFg = new JdWebFg();
        JdListFg jdListFg = new JdListFg();
        mFragments.add(jdWebFg);
        mFragments.add(jdListFg);

        JdOneAdapter jdOneAdapter = new JdOneAdapter(getActivity()
                .getSupportFragmentManager(), mFragments);
        nsvjdtwovp.setAdapter(jdOneAdapter);
        tljdtwotab.setupWithViewPager(nsvjdtwovp);


        return rootView;
    }
}
