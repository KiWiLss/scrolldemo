package com.kiwilss.scrolldemo.jingdong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.jingdong.ScrollOneActivity;
import com.kiwilss.scrolldemo.jingdong.adapter.IconFgAdapter2;
import com.kiwilss.scrolldemo.jingdong.widget.SlideDetailsLayout;
import com.kiwilss.scrolldemo.widget.GradationScrollView;
import com.kiwilss.scrolldemo.widget.NoScrollViewPager;

import java.util.ArrayList;

/**
 * FileName: IconFragment
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/27
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class IconFragment extends Fragment {
    private android.widget.ImageView ivicon;
    private com.kiwilss.scrolldemo.widget.GradationScrollView gsv;
    private com.kiwilss.scrolldemo.jingdong.widget.ItemWebView iwv;
    private com.kiwilss.scrolldemo.jingdong.widget.SlideDetailsLayout sdl;
    private int mHeight;
    private android.support.design.widget.TabLayout tlfgtab;
    private com.kiwilss.scrolldemo.widget.NoScrollViewPager nsvscrollonevp2;
    private ArrayList<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fg_icon, container, false);

        initView(rootView);

            nsvscrollonevp2.setNoScroll(true);

        //监听是在上半部分,还是在下半部分
        sdl.setOnSlideDetailsListener(new SlideDetailsLayout.OnSlideDetailsListener() {
            @Override
            public void onStatucChanged(SlideDetailsLayout.Status status) {

                if (status == SlideDetailsLayout.Status.CLOSE){
                    ((ScrollOneActivity)getActivity()).changeTitle(false);
                    ((ScrollOneActivity)getActivity()).changeViewPager(false);
                }else if (status == SlideDetailsLayout.Status.OPEN){
                   // Toast.makeText(getContext(), "open", Toast.LENGTH_SHORT).show();
                    //控制不可左右滑动
                    ((ScrollOneActivity)getActivity()).changeTitle(true);
                    ((ScrollOneActivity)getActivity()).changeViewPager(true);
                }
            }
        });
        //获取图片的高度
        ivicon.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mHeight = ivicon.getHeight();
                ivicon.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //监听scrollView的滚动
                gsv.setScrollViewListener(new GradationScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
                        ScrollOneActivity activity = (ScrollOneActivity) getActivity();
                        assert activity != null;
                        if (y <= 0){
                            activity.rlscrollonetitle1.setAlpha(1);
                            activity.rlscrollonetitle2.setAlpha(0);
                        }else if (y < mHeight){
                            float scale = (float) y / mHeight;
                            activity.rlscrollonetitle2.setAlpha((float) (scale));
                            activity.rlscrollonetitle1.setAlpha((float) (1-scale));
                        }else {
                            activity.rlscrollonetitle2.setAlpha(1);
                            activity.rlscrollonetitle1.setAlpha(0);
                        }

                    }
                });

            }
        });
       //初始化fragment
        initFragment();

        IconFgAdapter2 iconFgAdapter = new IconFgAdapter2(getActivity().
                getSupportFragmentManager(), mFragments);
        nsvscrollonevp2.setAdapter(iconFgAdapter);
        tlfgtab.setupWithViewPager(nsvscrollonevp2);



        return rootView;
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        WebViewFragment webViewFragment = new WebViewFragment();
        OtherFragment otherFragment = new OtherFragment();
        mFragments.add(webViewFragment);
        mFragments.add(otherFragment);
    }


    private void initView(View rootView) {
        this.sdl = (SlideDetailsLayout) rootView.findViewById(R.id.sdl);
        this.nsvscrollonevp2 = (NoScrollViewPager) rootView.findViewById(R.id.vp_scroll_one_vp2);
        this.tlfgtab = (TabLayout) rootView.findViewById(R.id.tl_fg_tab);
        this.gsv = (GradationScrollView) rootView.findViewById(R.id.gsv);
        this.ivicon = (ImageView) rootView.findViewById(R.id.iv_icon);
    }
}
