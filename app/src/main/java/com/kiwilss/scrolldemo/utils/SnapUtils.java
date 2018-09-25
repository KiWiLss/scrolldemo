package com.kiwilss.scrolldemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;

public class SnapUtils {
    //******************************************************************************************
    //                                                                            屏幕截图
    //******************************************************************************************
    /**获取指定Activity整个屏幕截图，这种方式只能获取当前应用的截图（连顶部状态栏中的时间等都获取不到）
     * @param withStatusBar 是否包含状态栏
     */
    public static Bitmap snapShot(Activity activity, boolean withStatusBar) {
        int statusBarHeight = 0;
        if (!withStatusBar) {
            Rect frame = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
            statusBarHeight = frame.top;
        }
        DisplayMetrics metric = new DisplayMetrics();
        ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metric);
        return snapShot(activity, 0, statusBarHeight, metric.widthPixels, metric.heightPixels - statusBarHeight);
    }
    /**
     * 获取指定Activity指定View的屏幕截图，这种方式只能获取当前应用的截图（连顶部状态栏中的时间等都获取不到）
     * @param activity
     * @param view
     * @return
     */
    public static Bitmap snapShot(Activity activity, View view) {
        return snapShot(activity, (int) view.getX(), (int) view.getY(), (int) view.getWidth(), (int) view.getHeight());
    }
    /**
     * 获取指定Activity指定区域的屏幕截图，这种方式只能获取当前应用的截图（连顶部状态栏中的时间等都获取不到）
     * @param activity        所要截取的Activity
     * @param x        The x coordinate of the first pixel in source
     * @param y        The y coordinate of the first pixel in source
     * @param width        The number of pixels in each row
     * @param height        The number of rows
     * @return        A copy of a subset of the source bitmap or the source bitmap itself.
     */
    public static Bitmap snapShot(Activity activity, int x, int y, int width, int height) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap cache = view.getDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(cache, x, y, width, height);
        view.destroyDrawingCache();
        return bitmap;
    }
    /**
     * 保存bitmap为图片
     * @param bitmap
     * @param fileName    文件名，注意是保存在了SD卡根目录下
     */
    public static void saveBitmap2Pic(Bitmap bitmap, String fileName) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath(), fileName);
            file.createNewFile();//在window中没问题，但是在Android上必须加这一句，否然报异常 java.io.FileNotFoundException: xxx: open failed: EROFS (Read-only file system)
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

