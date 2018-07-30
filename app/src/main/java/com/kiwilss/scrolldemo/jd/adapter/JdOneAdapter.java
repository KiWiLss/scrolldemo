package com.kiwilss.scrolldemo.jd.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kiwilss.scrolldemo.jingdong.widget.DragDetailFragmentPagerAdapter;

import java.util.List;

/**
 * FileName: JdOneAdapter
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/30
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class JdOneAdapter extends DragDetailFragmentPagerAdapter {

    private List<Fragment> mList;
    String mTitle[] = { "图文详情","规格参数" };

    public JdOneAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
