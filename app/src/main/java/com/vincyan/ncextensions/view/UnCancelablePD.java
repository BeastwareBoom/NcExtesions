package com.vincyan.ncextensions.view;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by xws on 2017/2/28.
 */

public class UnCancelablePD {

    private Context context;
    private ProgressDialog mDialog;

    public UnCancelablePD(Context context) {
        this.context = context;
    }


    public void show() {
        mDialog = new ProgressDialog(context);
        mDialog.setIndeterminate(true);
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public void cancel(){
        if (mDialog != null) {
            mDialog.cancel();
        }
    }
}
