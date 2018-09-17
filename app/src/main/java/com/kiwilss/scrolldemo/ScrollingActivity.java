package com.kiwilss.scrolldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kiwilss.scrolldemo.jd.JDTestActivity;
import com.kiwilss.scrolldemo.jd3.JDTransActivity;
import com.kiwilss.scrolldemo.jingdong.ScrollOneActivity;
import com.kiwilss.scrolldemo.test.ScrollTestActivity;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        int screenHeight = Utils.getScreenHeight(this);
        int screenHeight2 = Utils.getScreenHeight2(this);
        Log.e("MMM", "onCreate: "+screenHeight+"||"+screenHeight2 );





    }

//    @TargetApi(28)
//    public void getNotchParams() {
//        final View decorView = getWindow().getDecorView();
//
//        decorView.post(new Runnable() {
//            @Override
//            public void run() {
//                DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
//                Log.e("TAG", "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft());
//                Log.e("TAG", "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight());
//                Log.e("TAG", "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop());
//                Log.e("TAG", "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom());
//
//                List<Rect> rects = displayCutout.getBoundingRects();
//                if (rects == null || rects.size() == 0) {
//                    Log.e("TAG", "不是刘海屏");
//                } else {
//                    Log.e("TAG", "刘海屏数量:" + rects.size());
//                    for (Rect rect : rects) {
//                        Log.e("TAG", "刘海屏区域：" + rect);
//                    }
//                }
//            }
//        });
//    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void scrollOne(View view) {
        startActivity(new Intent(this, ScrollOneActivity.class));
    }

    public void scrollOneInter(View view) {
        startActivity(new Intent(this, ScrollTestActivity.class));
    }

    public void scrollOne2(View view) {
        startActivity(new Intent(this, JDTestActivity.class));
    }

    public void scrollOne3(View view) {
        startActivity(new Intent(this, JDTransActivity.class));
    }
}
