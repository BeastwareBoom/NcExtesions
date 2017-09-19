package com.vincyan.ncextensions;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.vincyan.ncextensions.base.MPermissionsActivity;
import com.xys.libzxing.zxing.activity.CaptureActivity;


public class WebViewActivity extends MPermissionsActivity {

    private WebView webView;
    public static final String TAG = "WebViewActivity";
    public static final String URL1 = "http://qll.jiahetianlang.com/mobile/";
    public static final String URL = "http://www.cst01.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);
        webView = (WebView) findViewById(R.id.wv);

//        initSettings();
//        webView.addJavascriptInterface(this, "android");
//        webView.loadUrl("file:///android_asset/scan.html");

        //为webview设置监听
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URL);


    }

    private void initSettings() {


        //夜间模式-false
//        webView.setDayOrNight(true);
        //不显示滚动条
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        WebSettings webSettings = webView.getSettings();

        //缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        //存储
        webSettings.setDomStorageEnabled(true);
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
    }

    public static final int REQUEST_SCAN_CODE = 0;

    //定义的方法
    @JavascriptInterface
    public void scan(String token) {
        Log.d(TAG, "scan: token = " + token);//object
//        Toast.makeText(WebViewActivity.this, token ,Toast.LENGTH_SHORT).show();
        //6.0以上
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermission(new String[]{Manifest.permission.CAMERA}, 0x0003);
        } else {
            startScan();
        }
    }

    /**
     * 扫描方法
     */
    private void startScan() {
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
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
            case 0x0003:
                startScan();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
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


    //设置回退键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
