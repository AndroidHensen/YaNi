package com.handsome.didi.Activity.Common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.handsome.didi.Base.BaseActivity;
import com.handsome.didi.R;

public class WebActivity extends BaseActivity {

    private WebView wv;
    private ProgressBar pb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initViews() {
        wv = findView(R.id.wv);
        pb = findView(R.id.pb);
    }

    @Override
    public void initListener() {
        wv.setWebViewClient(new MyWebViewClient());
        wv.setWebChromeClient(new MyWebChromeClient());
        wv.setDownloadListener(new MyDownLoadListener());
    }

    public void initData() {
        //初始化网络设置
        initWebViewSettings();
        //读取数据
        String url = getIntent().getStringExtra("url");
        wv.loadUrl(url);
    }


    @Override
    public void processClick(View v) {

    }

    /**
     * 初始化网络设置
     */
    private void initWebViewSettings() {
        WebSettings webSettings = wv.getSettings();
        //可以有缓存
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //设置支持页面js可用
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置允许访问文件数据
        webSettings.setAllowFileAccess(true);
        //可以使用localStorage
        webSettings.setDomStorageEnabled(true);
        //可以有数据库
        webSettings.setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setGeolocationDatabasePath(dir);
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
    }

    /**
     * webView渲染类
     */
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("http:") || url.startsWith("https:")) {
                return false;
            } else if (url.startsWith(WebView.SCHEME_TEL) ||
                    url.startsWith("sms:") ||
                    url.startsWith(WebView.SCHEME_MAILTO) ||
                    url.startsWith(WebView.SCHEME_GEO) ||
                    url.startsWith("maps:")) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException e) {
                }
            }
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            pb.setProgress(newProgress);
            //设置进度条
            if (newProgress == 100) {
                pb.setVisibility(View.INVISIBLE);
            } else {
                pb.setVisibility(View.VISIBLE);
            }
        }

        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            //定位服务
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
    }

    /**
     * webView下载类
     */
    private class MyDownLoadListener implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            //后退
            wv.goBack();
        } else {
            //退出
            finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        wv.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        wv.onPause();
    }
}
