package com.kiwilss.scrolldemo.jd.fg;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kiwilss.scrolldemo.R;

/**
 * FileName: JdWebFg
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/30
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class JdWebFg extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fg_test_web, container, false);

        WebView iwvwb = rootView.findViewById(R.id.wv);

        iwvwb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);//加载新的网页
                return true;
            }
        });

        iwvwb.getSettings().setJavaScriptEnabled(true);//支持加载javascript脚本

        iwvwb.loadUrl("https://www.baidu.com");
        return rootView;
    }
}
