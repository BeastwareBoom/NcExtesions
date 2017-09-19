package com.vincyan.ncextensions;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;

/**
 * 天朗信息技术有限公司
 *
 * @author liuzongze
 *         Created by Asus on 2017/6/29.
 * @link http://www.jiahetianlang.com
 * $desc$
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //加载 x5 内核
        QbSdk.initX5Environment(this, null);
    }
}
