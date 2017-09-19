package com.vincyan.ncextensions;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.vincyan.ncextensions.util.AppUtils;

import java.io.File;
import java.util.ArrayList;

public class WXShareActivity extends AppCompatActivity implements ImageLoader {

    private static final int IMAGE_PICKER = 100;
    private String mDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);


        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(this);   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }


    public void get(View view) {

        final View inflate = LayoutInflater.from(this).inflate(R.layout.input_dialog, null);

        new AlertDialog.Builder(this)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        EditText et = (EditText) inflate.findViewById(R.id.etInput);
                        mDesc = et.getText().toString();
                        if (TextUtils.isEmpty(mDesc)) {
                            Toast.makeText(WXShareActivity.this, "商品描述不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(WXShareActivity.this, ImageGridActivity.class);
                            startActivityForResult(intent, IMAGE_PICKER);
                        }
                    }
                })
                .setTitle("商品描述")
                .setView(inflate)
                .create()
                .show();
    }

    public void share(View view) {
        AppUtils.shareToFriends(this, "发给好友呗...");
    }


    ArrayList<Uri> mUris;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult: data = " + data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                for (int i = 0; i < images.size(); i++) {
                    ImageItem imageItem = images.get(i);

                    String path = imageItem.path;
                    Uri uri = Uri.fromFile(new File(path));
                    Log.d(TAG, "onActivityResult: uri = " + uri);
                    if (mUris == null) {
                        mUris = new ArrayList<>();
                    }

                    mUris.add(uri);
                }

                if (mUris != null) {
                    AppUtils.shareToTimeLine(this, mDesc, mUris);
                } else {
                    Toast.makeText(this, "未选择上传图片", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static final String TAG = "WXShareActivity";

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Log.d(TAG, "displayImage: path = " + path);
//        Picasso.with(activity)//
//                .load(new File(path))//
//                .placeholder(R.mipmap.default_image)//
//                .error(R.mipmap.default_image)//
//                .resize(width, height)//
//                .centerInside()//
//                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)//
//                .into(imageView);

        Glide
                .with(activity)
                .load(new File(path))
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
}
