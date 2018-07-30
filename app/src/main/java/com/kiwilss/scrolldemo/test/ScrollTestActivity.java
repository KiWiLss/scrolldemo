package com.kiwilss.scrolldemo.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.test.adapter.TestAdapter;
import com.kiwilss.scrolldemo.widget.DragScrollDetailsLayout;

import java.util.ArrayList;

/**
 * FileName: ScrollTestActivity
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/30
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class ScrollTestActivity extends AppCompatActivity {
    private android.widget.Button flagtips;
    private android.support.design.widget.TabLayout tablayout;
    private android.support.v4.view.ViewPager viewpager;
    private com.kiwilss.scrolldemo.widget.DragScrollDetailsLayout dragcontent;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);
        this.dragcontent = (DragScrollDetailsLayout) findViewById(R.id.drag_content);
        this.viewpager = (ViewPager) findViewById(R.id.viewpager);
        this.tablayout = (TabLayout) findViewById(R.id.tablayout);
        this.flagtips = (Button) findViewById(R.id.flag_tips);


        initFragment();

        TestAdapter testAdapter = new TestAdapter(getSupportFragmentManager(), mFragments);
        viewpager.setAdapter(testAdapter);

        tablayout.setupWithViewPager(viewpager);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        WebFg webFg = new WebFg();
        ListFg listFg = new ListFg();
        mFragments.add(webFg);
        mFragments.add(listFg);
    }
}
