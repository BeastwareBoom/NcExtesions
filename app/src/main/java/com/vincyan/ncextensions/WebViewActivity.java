package com.vincyan.ncextensions;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vincyan.ncextensions.base.MPermissionsActivity;
import com.xys.libzxing.zxing.activity.CaptureActivity;

//import com.tencent.smtt.sdk.WebSettings;
//import com.tencent.smtt.sdk.WebView;


public class WebViewActivity extends MPermissionsActivity {

    private WebView webView;
    private ProgressBar pg1;
    public static final String TAG = "WebViewActivity";
    public static final String URL1 = "http://qll.jiahetianlang.com/mobile/";
    public static final String URL = "http://www.cst01.com/";
    public static final String URL2 = "http://www.cst01.com/hjb/index.html";

    public static final String LOCAL_HTML = "file:///android_asset/scan.html";

    private TextView progress;
    public static final int REQUEST_SCAN_CODE = 0;
    public static final int REQUEST_CAMERA_PERMISSION = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);
        webView = (WebView) findViewById(R.id.wv);
        pg1 = (ProgressBar) findViewById(R.id.progressBar1);
        progress = (TextView) findViewById(R.id.progress);

        initSettings();
        webView.addJavascriptInterface(this, "android");
//        webView.loadUrl(LOCAL_HTML);
        webView.loadUrl(URL);

        //若需要传递参数
//        byte [] postData = "username=vintage&password=12345".getBytes();
//        webView.postUrl(LOCAL_HTML,postData);

    }

    private void initSettings() {

        webView.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //为webview设置监听
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    pg1.setVisibility(View.GONE);//加载完网页进度条消失
                    progress.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progress.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progress.setText(newProgress + "%");//设置进度值
                }
            }
        });


        //夜间模式-false
//        webView.setDayOrNight(true);
        //不显示滚动条
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        WebSettings webSettings = webView.getSettings();

        //缓存策略：存在则读取否则加载网络
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        //存储
        webSettings.setDatabaseEnabled(true);
        //不显示缩放按钮
        webSettings.setDisplayZoomControls(false);

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        //开启JavaScript支持
        webSettings.setJavaScriptEnabled(true);
        // enable navigator.geolocation
        webSettings.setGeolocationEnabled(true);

        //解决5.0部分图片不显示
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportMultipleWindows(true);

        //【解决html5网页不能加载（空白或显示不完全）】
        webSettings.setDomStorageEnabled(true);
        webSettings.setBlockNetworkImage(false);//解决图片不显示
    }


    //定义的方法
    @JavascriptInterface
    public void scan(String token) {
        Log.d(TAG, "scan: token = " + token);//object
//        Toast.makeText(WebViewActivity.this, token ,Toast.LENGTH_SHORT).show();
        //6.0以上
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermission(new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            startScan();
        }
    }

    //获取客户端类型的方法
    @JavascriptInterface
    public String getClientType() {
        return "android";
    }

    /**
     * 扫描方法
     */
    private void startScan() {
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, REQUEST_SCAN_CODE);
    }

    /**
     * 权限成功回调函数
     *
     * @param requestCode
     */
    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                startScan();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null || resultCode != RESULT_OK) {
            return;
        }

        Bundle bundle = data.getExtras();
        String scanResult = bundle.getString("result");
        Log.d(TAG, "onActivityResult: scanResult = " + scanResult);

        if (scanResult != null) {
            webView.loadUrl(scanResult);
        }
    }

    //定义的方法
    @JavascriptInterface
    public void login(String _type, String username, String password) {
        Log.d(TAG, "login: _type = " + _type);//object
        Log.d(TAG, "login: username = " + username + " , password = " + password);

        Toast.makeText(WebViewActivity.this, "_type:" + _type
                + "\nusername:" + username + "\npassword:" + password, Toast.LENGTH_SHORT).show();
    }

    //定义的方法
    @JavascriptInterface
    public void print(String value) {
        Log.d(TAG, "print: value = " + value);
    }

    private long time;

    //设置回退键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //若位于首页
            String url = webView.getUrl();
            Log.d(TAG, "onKeyDown: url = " + url);
            if (url.equals(URL)) {
                if (System.currentTimeMillis() - time < 3000) {
                    return super.onKeyDown(keyCode, event);
                } else {
                    Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    time = System.currentTimeMillis();
                    return true;
                }
            } else {
                //不在首页时，执行WebView后退
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
