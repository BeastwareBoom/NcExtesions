package com.vincyan.ncextensions.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by xws on 2017/1/13.
 */

public class AppUtils {

    private static final int QUN_QUEST = 200;

    public static void shareToFriends(Activity context, String msg) {
        try {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
            sendIntent.setType("text/plain");
            sendIntent.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");//微信朋友
            sendIntent.setClassName("com.tencent.mobileqq", "cooperation.qqfav.widget.QfavJumpActivity");//保存到QQ收藏
            sendIntent.setClassName("com.tencent.mobileqq", "cooperation.qlink.QlinkShareJumpActivity");//QQ面对面快传
            sendIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.qfileJumpActivity");//传给我的电脑
            sendIntent.setClassName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");//QQ好友或QQ群
            sendIntent.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");//微信朋友圈，仅支持分享图片
            context.startActivityForResult(sendIntent, QUN_QUEST);
        } catch (ActivityNotFoundException e) {
            String message = e.getMessage();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static void shareToTimeLine(Context context, String title, ArrayList<Uri> uris) {

        try {
            Intent intent = new Intent();
            ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            intent.setComponent(comp);
            intent.setAction(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("image/*");

            intent.putExtra("Kdescription", title);
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
            context.startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(context, "未安装微信客户端", Toast.LENGTH_SHORT).show();
        }

    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it  Or Log it.
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

}
