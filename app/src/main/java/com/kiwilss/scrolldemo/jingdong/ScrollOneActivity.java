package com.kiwilss.scrolldemo.jingdong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.jingdong.adapter.ScrollOneAdapter;
import com.kiwilss.scrolldemo.jingdong.fragment.IconFragment;
import com.kiwilss.scrolldemo.jingdong.fragment.TwoFragment;
import com.kiwilss.scrolldemo.widget.NoScrollViewPager;

import java.util.ArrayList;

/**
 * FileName: ScrollOneActivity
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/27
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class ScrollOneActivity extends AppCompatActivity {
    private android.widget.RelativeLayout rlscrollonebottom;
    public com.kiwilss.scrolldemo.widget.NoScrollViewPager nsvscrollonevp;
    private android.widget.ImageView ivscrolloneback1;
    private android.widget.ImageView ivscrolloneshare1;
    public android.widget.RelativeLayout rlscrollonetitle1;
    private android.widget.ImageView ivscrolloneback2;
    private android.widget.ImageView ivscrolloneshare2;
    private android.support.design.widget.TabLayout tlscrollonetab;

    public android.widget.TextView tvscrolloneinfo;

    public android.widget.RelativeLayout rlscrollonetitle2;
    private ArrayList<Fragment> mFragments;



    float mAlpha1 = 1 ;
    float mAlpha2 = 0;
    int mPos;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_one);

        initView();
        initFragment();

        ScrollOneAdapter adapter = new ScrollOneAdapter(getSupportFragmentManager(), mFragments);
        nsvscrollonevp.setAdapter(adapter);
        tlscrollonetab.setupWithViewPager(nsvscrollonevp);

        //viewPager滑动监听
        nsvscrollonevp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0){
                    rlscrollonetitle1.setAlpha(mAlpha1-positionOffset);
                    rlscrollonetitle2.setAlpha(positionOffset+mAlpha2);
                }else {
                    //postion == 1, ppositionOffset == 0
                    rlscrollonetitle1.setAlpha(0);
                    rlscrollonetitle2.setAlpha(1);
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
                        mAlpha1 = rlscrollonetitle1.getAlpha();
                        mAlpha2 = rlscrollonetitle2.getAlpha();
                    }
                }
            }
        });
    }

    public void changeViewPager(boolean isOpen){
        if (isOpen){
            nsvscrollonevp.setNoScroll(true);
        }else {
            nsvscrollonevp.setNoScroll(false);
        }
    }

    public void changeTitle(boolean isOpen){
        //显示图文详情,标题
        if (isOpen){
            tvscrolloneinfo.setVisibility(View.VISIBLE);
            tlscrollonetab.setVisibility(View.GONE);
        }else {
            tvscrolloneinfo.setVisibility(View.GONE);
            tlscrollonetab.setVisibility(View.VISIBLE);
        }
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        IconFragment iconFragment = new IconFragment();
        TwoFragment twoFragment = new TwoFragment();
        mFragments.add(iconFragment);
        mFragments.add(twoFragment);
    }

    private void initView() {
        this.rlscrollonetitle2 = (RelativeLayout) findViewById(R.id.rl_scroll_one_title2);
        this.tvscrolloneinfo = (TextView) findViewById(R.id.tv_scroll_one_info);
        this.tlscrollonetab = (TabLayout) findViewById(R.id.tl_scroll_one_tab);
        this.ivscrolloneshare2 = (ImageView) findViewById(R.id.iv_scroll_one_share2);
        this.ivscrolloneback2 = (ImageView) findViewById(R.id.iv_scroll_one_back2);
        this.rlscrollonetitle1 = (RelativeLayout) findViewById(R.id.rl_scroll_one_title1);
        this.ivscrolloneshare1 = (ImageView) findViewById(R.id.iv_scroll_one_share1);
        this.ivscrolloneback1 = (ImageView) findViewById(R.id.iv_scroll_one_back1);
        this.nsvscrollonevp = (NoScrollViewPager) findViewById(R.id.nsv_scroll_one_vp);
        this.rlscrollonebottom = (RelativeLayout) findViewById(R.id.rl_scroll_one_bottom);
    }
}
