package com.kiwilss.scrolldemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

/**
 * FileName: MyApp
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/9/25
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class MyApp extends Application {
    private int mCount;

    @Override
    public void onCreate() {
        super.onCreate();

        mCount = 0;

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                mCount++;
                //如果mCount==1，说明是从后台到前台
                if (mCount == 1) {
                    //执行app跳转到前台的逻辑
                    Toast.makeText(activity, "进入前台", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                mCount--;
                //如果mCount==0，说明是前台到后台
                if (mCount == 0) {
                    //执行应用切换到后台的逻辑
                    Toast.makeText(activity, "退出到后台", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });


    }



}
