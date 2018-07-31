package com.kiwilss.scrolldemo.jd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.jd.adapter.JdOneAdapter;
import com.kiwilss.scrolldemo.jd.fg.JdOneFg;
import com.kiwilss.scrolldemo.jd.fg.JdTwoFg;
import com.kiwilss.scrolldemo.widget.NoScrollViewPager;

import java.util.ArrayList;

/**
 * FileName: JDTestActivity
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/30
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class JDTestActivity extends AppCompatActivity {
    private android.widget.RelativeLayout rljdtestbottom;
    private com.kiwilss.scrolldemo.widget.NoScrollViewPager nsvjdtestvp;
    private android.widget.ImageView ivjdtestback1;
    private android.widget.ImageView ivjdtestshare1;
    public android.widget.RelativeLayout rljdtesttitle1;
    private android.widget.ImageView ivjdtestback2;
    private android.widget.ImageView ivjdtestshare2;
    private android.support.design.widget.TabLayout tljdtesttab;
    private android.widget.TextView tvjdtestinfo;
    public android.widget.RelativeLayout rljdtesttitle2;
    private ArrayList<Fragment> mFragments;
    private int mPos;
    private float mAlpha1;
    private float mAlpha2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd_test);
        this.rljdtesttitle2 = (RelativeLayout) findViewById(R.id.rl_jd_test_title2);
        this.tvjdtestinfo = (TextView) findViewById(R.id.tv_jd_test_info);
        this.tljdtesttab = (TabLayout) findViewById(R.id.tl_jd_test_tab);
        this.ivjdtestshare2 = (ImageView) findViewById(R.id.iv_jd_test_share2);
        this.ivjdtestback2 = (ImageView) findViewById(R.id.iv_jd_test_back2);
        this.rljdtesttitle1 = (RelativeLayout) findViewById(R.id.rl_jd_test_title1);
        this.ivjdtestshare1 = (ImageView) findViewById(R.id.iv_jd_test_share1);
        this.ivjdtestback1 = (ImageView) findViewById(R.id.iv_jd_test_back1);
        this.nsvjdtestvp = (NoScrollViewPager) findViewById(R.id.nsv_jd_test_vp);
        this.rljdtestbottom = (RelativeLayout) findViewById(R.id.rl_jd_test_bottom);


        initFragment();

        JdOneAdapter jdOneAdapter = new JdOneAdapter(getSupportFragmentManager(), mFragments);
        nsvjdtestvp.setAdapter(jdOneAdapter);
        tljdtesttab.setupWithViewPager(nsvjdtestvp);

        nsvjdtestvp.setOffscreenPageLimit(mFragments.size());
        tljdtesttab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                //记录标题初始透明度值
                if (position == 1){
                    mAlpha1 = rljdtesttitle1.getAlpha();
                    mAlpha2 = rljdtesttitle2.getAlpha();
                }
                Log.e("MMM", "onTabSelected: "+position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //viewPager滑动监听
        nsvjdtestvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("MMM", "onPageScrolled: "+position+"||"+positionOffset );
                if (position == 0){
                    rljdtesttitle1.setAlpha(mAlpha1-positionOffset);
                    rljdtesttitle2.setAlpha(positionOffset+mAlpha2);
                }else {
                    //postion == 1, ppositionOffset == 0
                    rljdtesttitle1.setAlpha(0);
                    rljdtesttitle2.setAlpha(1);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == 1){
                    //开始拖动
                    if (mPos == 0){
                        Log.e("MMM", "onPageScrollStateChanged: "+mAlpha1+"||"+mAlpha2);
                        mAlpha1 = rljdtesttitle1.getAlpha();
                        mAlpha2 = rljdtesttitle2.getAlpha();
                    }
                }
            }
        });
    }


    public void changeViewPager(boolean isOpen){
        if (isOpen){
            nsvjdtestvp.setNoScroll(true);
        }else {
            nsvjdtestvp.setNoScroll(false);
        }
    }

    public void changeTitle(boolean isOpen){
        //显示图文详情,标题
        if (isOpen){
            tvjdtestinfo.setVisibility(View.VISIBLE);
            tljdtesttab.setVisibility(View.GONE);
        }else {
            tvjdtestinfo.setVisibility(View.GONE);
            tljdtesttab.setVisibility(View.VISIBLE);
        }
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        JdOneFg jdOneFg = new JdOneFg();
        JdTwoFg jdTwoFg = new JdTwoFg();
        mFragments.add(jdOneFg);
        mFragments.add(jdTwoFg);
    }
}
