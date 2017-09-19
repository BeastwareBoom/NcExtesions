package com.vincyan.ncextensions;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vincyan.ncextensions.base.MPermissionsActivity;
import com.vincyan.ncextensions.util.AppUtils;
import com.vincyan.ncextensions.util.ScreenUtil;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class PermissionActivity extends MPermissionsActivity {

    private static final int SELELCT_PIC = 0x01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: count = " + count);
        Point screenSize = ScreenUtil.getScreenSize(this);
        Log.d(TAG, "RefreshView: screenSize = " + screenSize);
    }

    public void startListViewAct(View view) {
        startActivity(new Intent(this, ListViewEditTextActivity.class));
    }

    public void selectImageActivity(View view) {
        startActivity(new Intent(this, WXShareActivity.class));
    }

    private static final int FILE_SELECT_CODE = 10;

    public void showFileChooser(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("*/*");
        intent.setType("application/octet-stream");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    //自定义键盘
    public void customizeKeyboard(View view) {
        startActivity(new Intent(this, KeyboardActivity.class));
//        new UnCancelablePD(this).show();
    }

    private int count;

    //启动当前activity
    public void startSelf(View view) {
        count++;
        startActivity(new Intent(this, PermissionActivity.class));
    }


    //横向滑动RecyclerView
    public void startOkHttpUtils(View view) {
        startActivity(new Intent(this, OkHttpActivity.class));
    }

    //二维码扫描
    public void qrScan(View view) {
//        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
//        startActivityForResult(openCameraIntent, 0);
    }

    /*
    D/PermissionActivity: onCreate: count = 0
    D/PermissionActivity: onCreate: count = 0
    D/PermissionActivity: onCreate: count = 0

    D/PermissionActivity: onDestroy: count = 0
    D/PermissionActivity: onDestroy: count = 1
    D/PermissionActivity: onDestroy: count = 1

     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: count = " + count);
    }

    public void videoActivity(View v) {
        startActivity(new Intent(this, VideoViewActivity.class));
    }

    public void shareToWX(View view) {


        startActivity(new Intent(this, WebViewActivity.class));
        /*Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image*//*");
        startActivityForResult(intent, SELELCT_PIC);*/
    }

    public static final String TAG = "PermissionActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: requestCode = " + requestCode);
        Log.d(TAG, "onActivityResult: resultCode = " + resultCode);

        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case FILE_SELECT_CODE:
// Get the Uri of the selected file
                Uri uri = data.getData();
                Log.d(TAG, "File Uri: " + uri.toString());
                // Get the path
                String path = null;
                try {
                    path = AppUtils.getPath(this, uri);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "File Path: " + path);
                // Get the file instance
                // File file = new File(path);
                // Initiate the upload
                break;
            case 0:
                Bundle bundle = data.getExtras();
                String scanResult = bundle.getString("result");
                Log.d(TAG, "onActivityResult: scanResult = " + scanResult);
                break;
            default:
                Uri uri2 = data.getData();
                Log.d(TAG, "onActivityResult: uri2 = " + uri2);
                ArrayList<Uri> list = new ArrayList<>();
                list.add(uri2);
                shareToTimeLine(this, "发个朋友圈...", list);
                break;
        }

    }

    private void shareToTimeLine(Context context, String title, ArrayList<Uri> uris) {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");

        intent.putExtra("Kdescription", title);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        context.startActivity(intent);
    }

    /**
     * 打电话
     *
     * @param view
     */
    public void onClick1(View view) {
        requestPermission(new String[]{Manifest.permission.CALL_PHONE}, 0x0001);
    }

    public void newsList(View view) {
        Intent intent = new Intent(this, NewsListActivity.class);
        startActivity(intent);
    }

    /**
     * 写SD卡
     *
     * @param view
     */
    public void onClick2(View view) {
        requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x0002);
    }

    /**
     * 拍照
     *
     * @param view
     */
    public void onClick3(View view) {
        requestPermission(new String[]{Manifest.permission.CAMERA}, 0x0003);
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
            case 0x0001:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:15621187256"));
                startActivity(intent);
                break;
        }
    }

    public void startSpannableAct(View view) {
        startActivity(new Intent(this, TextViewSpannableActivity.class));
    }

    /*
    void method() {
        //recyclervice的滑动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //根据下面实时获取到的滑动位置进行监听。如果到底部了,进行刷新数据
                //判断条件：1、滑动到底部；2、状态保持稳定，不上下滑动了
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1
                        == recycle_adapter.getItemCount()) {
                    //加载数据
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                //当一滑动时，获取到当前的位置
                lastVisibleItem = lm.findLastVisibleItemPosition();
            }
        });
    }
    */
}
