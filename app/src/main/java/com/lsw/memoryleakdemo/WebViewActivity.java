package com.lsw.memoryleakdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * webview内存泄露
 */
public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = (WebView) findViewById(R.id.web);
        mWebView.loadUrl("http://www.cnblogs.com/whoislcj/p/5720202.html");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //避免泄露
        destroyWebView();
        android.os.Process.killProcess(android.os.Process.myPid());
        MemoryLeakDemoApplication.getWatcher().watch(this);
    }

    private void destroyWebView() {
        if (mWebView != null) {
            mWebView.pauseTimers();
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
