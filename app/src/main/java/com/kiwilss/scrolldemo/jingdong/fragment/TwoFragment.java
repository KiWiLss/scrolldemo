package com.kiwilss.scrolldemo.jingdong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.jingdong.adapter.IconFgAdapter;
import com.kiwilss.scrolldemo.widget.NoScrollViewPager;

import java.util.ArrayList;

/**
 * FileName: TwoFragment
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/27
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class TwoFragment extends Fragment {
    private TabLayout tlfgtab;
    private NoScrollViewPager nsvscrollonevp;
    private ArrayList<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fg_two, container, false);
        this.nsvscrollonevp =  rootView.findViewById(R.id.nsv_scroll_one_vp2);
        this.tlfgtab = (TabLayout) rootView.findViewById(R.id.tl_fg_tab);


        nsvscrollonevp.setNoScroll(true);
        //初始化fragment
        initFragment();




        return rootView;
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        WebViewFragment webViewFragment = new WebViewFragment();
        OtherFragment otherFragment = new OtherFragment();
        mFragments.add(webViewFragment);
        mFragments.add(otherFragment);
        Log.e("MMM", "initFragment: "+mFragments.size());

        IconFgAdapter iconFgAdapter = new IconFgAdapter
                (getActivity().getSupportFragmentManager(), mFragments);
        nsvscrollonevp.setAdapter(iconFgAdapter);
        tlfgtab.setupWithViewPager(nsvscrollonevp);
    }
}
