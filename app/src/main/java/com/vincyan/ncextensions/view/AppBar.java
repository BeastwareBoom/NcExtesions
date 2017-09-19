package com.vincyan.ncextensions.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vincyan.ncextensions.R;

/**
 * Created by xws on 2016/11/23.
 */

public class AppBar extends LinearLayout {

    public static final String TAG = AppBar.class.getSimpleName();

    private TextView text;

    public AppBar(Context context) {
        super(context);
        init(context, null);
    }

    public AppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AppBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void init(final Context context, AttributeSet attrs) {
        View view = View.inflate(context, R.layout.app_bar, this);
        text = (TextView) view.findViewById(R.id.tvTitle);
        view.findViewById(R.id.ivBack).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof Activity) {
                    ((Activity) context).finish();
                }
            }
        });

        if (attrs != null) {
            /*TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.AppBar);
            String textValue = t.getString(R.styleable.AppBar_textTitle);
            Log.d(TAG, "init: textValue = " + textValue);
            text.setText(textValue);
            t.recycle();*/
            String textTitle = attrs.getAttributeValue("http://schemas.android.com/apk/com.vincyan.ncextensions", "textTitle");
            text.setText(textTitle);
        }

        /*int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String attrName = attrs.getAttributeName(i);
            String attrVal = attrs.getAttributeValue(i);
            Log.e(TAG, "attrName = " + attrName + " , attrVal = " + attrVal);
        }*/
    }
}
