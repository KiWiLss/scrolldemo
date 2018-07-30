package com.kiwilss.scrolldemo.jingdong.fragment;

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
import com.kiwilss.scrolldemo.jingdong.widget.ItemWebView;

/**
 * FileName: WebViewFragment
 *
 * @author : Lss kiwilss
 * e-mail : kiwilss@163.com
 * time   : 2018/7/27
 * desc   : ${DESCRIPTION}
 * Description: ${DESCRIPTION}
 */
public class WebViewFragment extends Fragment {
    private ItemWebView iwvwb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fg_webview, container, false);
        this.iwvwb = (ItemWebView) rootView.findViewById(R.id.iwv_wb);

        //Toast.makeText(getActivity(), "webviewfg", Toast.LENGTH_SHORT).show();
        iwvwb.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);//加载新的网页
                return true;
            }
        });

        iwvwb.getSettings().setJavaScriptEnabled(true);//支持加载javascript脚本

        iwvwb.loadUrl("https://www.baidu.com");
        //iwvwb.loadUrl("https://www.jianshu.com/p/0fc1d9223d3d");

        return rootView;
    }
}
