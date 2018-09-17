package com.kiwilss.scrolldemo.jd3.fg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.jd.adapter.JdOneAdapter;
import com.kiwilss.scrolldemo.jd3.JDTransActivity;
import com.kiwilss.scrolldemo.widget.DragScrollDetailsLayout;
import com.kiwilss.scrolldemo.widget.GradationScrollView;

import java.util.ArrayList;

/**
 * FileName: JdOneFg
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/30
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class JdOneFg extends Fragment {
    private ImageView ivjdicon;
    private GradationScrollView jdgsv;
    private TabLayout tljdtab;
    private ViewPager vpjdvp;
    private DragScrollDetailsLayout jddsdl;
    private ArrayList<Fragment> mFragments;
    private int mHeight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fg_jd_one3, container, false);
        this.jddsdl = (DragScrollDetailsLayout) rootView.findViewById(R.id.jd_dsdl);
        this.vpjdvp = (ViewPager) rootView.findViewById(R.id.vp_jd_vp);
        this.tljdtab = (TabLayout) rootView.findViewById(R.id.tl_jd_tab);
        this.jdgsv = (GradationScrollView) rootView.findViewById(R.id.jd_gsv);
        this.ivjdicon = (ImageView) rootView.findViewById(R.id.iv_jd_icon);


        initFragment();

        JdOneAdapter jdOneAdapter = new JdOneAdapter(getActivity()
                .getSupportFragmentManager(), mFragments);
        vpjdvp.setAdapter(jdOneAdapter);
        tljdtab.setupWithViewPager(vpjdvp);

        //监控是在上半部分,还是下半部分,
        // 上半部分控制ViewPager可以滑动,下半部分控制ViewPager不可滑动
        //改变标题
        jddsdl.setOnSlideDetailsListener(new DragScrollDetailsLayout.OnSlideFinishListener() {
            @Override
            public void onStatueChanged(DragScrollDetailsLayout.CurrentTargetIndex status) {
                JDTransActivity activity = (JDTransActivity) getActivity();
                //下半部分
                if (status == DragScrollDetailsLayout.CurrentTargetIndex.DOWNSTAIRS){
                    activity.changeTitle(true);
                    activity.changeViewPager(true);
                }else if (status == DragScrollDetailsLayout.CurrentTargetIndex.UPSTAIRS){
                    //上半部分
                    activity.changeTitle(false);
                    activity.changeViewPager(false);
                }
            }
        });


//获取图片的高度
        ivjdicon.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeight = ivjdicon.getHeight();
                ivjdicon.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //监听scrollView的滚动
                jdgsv.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
                        JDTransActivity activity = (JDTransActivity) getActivity();
                        assert activity != null;
                        if (y <= 0){
                            activity.rljdtesttitle1.setAlpha(1);
                            activity.rljdtesttitle2.setAlpha(0);
                        }else if (y < mHeight){
                            float scale = (float) y / mHeight;
                            activity.rljdtesttitle2.setAlpha((float) (scale));
                            activity.rljdtesttitle1.setAlpha((float) (1-scale));
                        }else {
                            activity.rljdtesttitle2.setAlpha(1);
                            activity.rljdtesttitle1.setAlpha(0);
                        }

                    }
                });

            }
        });


        return rootView;
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        JdWebFg jdWebFg = new JdWebFg();
        JdListFg jdListFg = new JdListFg();
        mFragments.add(jdWebFg);
        mFragments.add(jdListFg);
    }
}
