package com.kiwilss.scrolldemo.activity_result;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.kiwilss.scrolldemo.R;
import com.kiwilss.scrolldemo.utils.SnapUtils;

import java.nio.ByteBuffer;

/**
 * FileName: ResultTestActivity
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/9/17
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class ResultTestActivity extends AppCompatActivity {
    private android.widget.ImageView ivresulticon;
    private int width;
    private int height;
    private int dpi;
    private ImageReader imageReader;
    private MediaProjectionManager manager;
    private VirtualDisplay display;
    private MediaProjection projection;
    static Handler handler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_test);
        this.ivresulticon = (ImageView) findViewById(R.id.iv_result_icon);

        //app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void finishListener(View view) {
//        setResult(RESULT_OK);
//        finish();

//        Bitmap bitmap = Utils.snapShotWithoutStatusBar(this);
//        ivresulticon.setImageBitmap(bitmap);

//        Bitmap bitmap = Utils.snapShotWithStatusBar(this);
//        ivresulticon.setImageBitmap(bitmap);

//        //获取屏幕的宽高和DPI
//        Display display = getWindowManager().getDefaultDisplay();
//        DisplayMetrics metrics = new DisplayMetrics();
//        display.getMetrics(metrics);
//        width = metrics.widthPixels;
//        height = metrics.heightPixels;
//        dpi = metrics.densityDpi;
//        //初始化ImageReader实例
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            imageReader = ImageReader.newInstance(width, height, PixelFormat.RGBA_8888, 2);
//        }
//        //获取MediaProjectionManager实例
//        manager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
//        //启动截屏Activity【com.android.systemui.media.MediaProjectionPermissionActivity】
//        startActivityForResult(manager.createScreenCaptureIntent(), 200);

        Bitmap bitmap = SnapUtils.snapShot(this, true);
        ivresulticon.setImageBitmap(bitmap);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 200) {
            //获取MediaProjection实例
            projection = manager.getMediaProjection(RESULT_OK, data);
            //获取虚拟显示器VirtualDisplay的实例
            display = projection.createVirtualDisplay("ScreenShot",
                    width, height, dpi,
                    DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                    imageReader.getSurface(),
                    null, null);
            //获取截图
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //bitmap即为所得
                    Bitmap bitmap = takeScreenShot();
                    ivresulticon.setImageBitmap(bitmap);
                }
            }, 1000);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private Bitmap takeScreenShot() {
        Bitmap bitmap = null;
        try {
            Image image = imageReader.acquireLatestImage();
            int width = image.getWidth();
            int height = image.getHeight();
            Image.Plane[] planes = image.getPlanes();
            ByteBuffer buffer = planes[0].getBuffer();
            int pixelStride = planes[0].getPixelStride();
            int rowStride = planes[0].getRowStride();
            int rowPadding = rowStride - pixelStride * width;
            bitmap = Bitmap.createBitmap(width + rowPadding / pixelStride,
                    height, Bitmap.Config.ARGB_8888);
            bitmap.copyPixelsFromBuffer(buffer);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height);
            image.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (imageReader != null) {
                imageReader.close();
            }
            if (projection != null) {
                projection.stop();
            }
            if (display != null) {
                display.release();//释放
            }
        }
        return bitmap;
    }

    }
