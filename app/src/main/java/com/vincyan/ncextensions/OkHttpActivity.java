package com.vincyan.ncextensions;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.vincyan.ncextensions.util.BeanCallback;
import com.vincyan.ncextensions.util.OkHttpUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class OkHttpActivity extends BaseActivity {


    public static final String REQUEST_URL = "http://java.bizpower.com/api";
    public static final String REQUEST_URL2 = "http://192.168.1.234/api/goods/293";
    public static final String TAG = "OkHttpActivity";
    protected TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        textView = (TextView) findViewById(R.id.tv);

    }

    /**
     * 延迟2s执行请求
     *
     * @param view
     */
    public void request(final View view) {

        //延迟2s执行网络请求
        view.postDelayed(new Runnable() {
            @Override
            public void run() {

                //普通网络请求
                get();
                //destroy中修改标记，不接收响应
                OkHttpUtil.getAsyn(OkHttpActivity.this, REQUEST_URL, new BeanCallback<String>() {
                    @Override
                    public void response(String data) {
                        Log.d(TAG, "response: 修改标记 data = " + data);
                    }
                });

            }
        }, 2000);


    }

    private void get() {
        OkHttpUtils
                .get()
                .url(REQUEST_URL)
                //当前Activity作为tag
                .tag(this)
                .build()
//                .execute(new BeanCallback<String>() {
//                    @Override
//                    public void response(String data) {
//                        Log.d(TAG, "response: data = " + data);
//                    }
//                });
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Log.e(TAG, "onError: e = " + e);
                    }

                    @Override
                    public void onResponse(final String s, int code) {
                        Log.d(TAG, "onResponse: textView == null : " + (textView == null));
                        Log.d(TAG, "onResponse: s = " + s);
                    }
                });
        //取消以Activity.this作为tag的请求
//        OkHttpUtils.getInstance().cancelTag(this);
    }

    @Override
    protected void onDestroy() {
        textView = null;//手动置空
        OkHttpUtils.getInstance().cancelTag(this);//取消以Activity.this作为tag的请求
        super.onDestroy();
    }
}
