package com.kiwilss.scrolldemo.test.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kiwilss.scrolldemo.jingdong.widget.DragDetailFragmentPagerAdapter;

import java.util.List;

/**
 * FileName: TestAdapter
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/30
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class TestAdapter extends DragDetailFragmentPagerAdapter {

    private List<Fragment>mData;
    String mTitle[] = { "图文详情","规格参数" };

    public TestAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        mData = data;
    }


    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
