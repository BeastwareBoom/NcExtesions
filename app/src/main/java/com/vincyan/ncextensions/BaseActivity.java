package com.vincyan.ncextensions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhy.http.okhttp.OkHttpUtils;

public class BaseActivity extends AppCompatActivity {

    protected boolean isOnDestroy;//是否拒绝网络请求的响应；true表示拒绝；false表示接收，默认false，在onDestroy中设置为true。

    public boolean isOnDestroy() {
        return isOnDestroy;
    }

    public void setOnDestroy(boolean onDestroy) {
        isOnDestroy = onDestroy;
    }

    @Override
    protected void onDestroy() {
        //1.取消请求
        OkHttpUtils.getInstance().cancelTag(this);
        //2.拒绝响应
        setOnDestroy(true);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
