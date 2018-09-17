package com.kiwilss.scrolldemo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * FileName: Utils
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/9/17
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class Utils {


    /**
     * 获取屏幕的高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager service = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        service.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


    public static int getScreenHeight2(Context context){
        int  result = 0;

        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (0 < identifier) {
            result = context.getResources().getDimensionPixelOffset(identifier);
            //result = context.resources.getDimensionPixelOffset(resourceId)
        }
        return result;
    }


    public static boolean hasNotchInScreen(Context context) {
        boolean ret = false;
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("test", "hasNotchInScreen ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("test", "hasNotchInScreen NoSuchMethodException");
        } catch (Exception e) {
            Log.e("test", "hasNotchInScreen Exception");
        } finally {
            return ret;
        }
    }


}
