package com.vincyan.ncextensions.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/6/9 15:07
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description 重写高度测量，使用ScrollView包裹
 */
public class MyListView extends ListView {

    public static final String TAG = MyListView.class.getSimpleName();

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
