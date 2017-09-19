package com.vincyan.ncextensions;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.unionpay.UPPayAssistEx;
import com.vincyan.ncextensions.view.AppBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


public class UnionPayActivity extends AppCompatActivity implements Runnable, Handler.Callback {

    private static final int POPUPWINDOW = 0x1;
    private static final int COPY_TEXT = 0x2;
    private PopupWindow popupWindow;
    private Handler mHandler;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler(this);
        mEditText = (EditText) findViewById(R.id.et);
        pay();

        AppBar bar = (AppBar) findViewById(R.id.ab);
//        bar.text.setText("为啥呢");
    }


    /*****************************************************************
     * mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境,该环境中不发生真实交易
     *****************************************************************/
    private final String mMode = "01";
    // TODO: 2016/11/8  修改正式环境

    //交易订单号
    private String tn = "01";

    /*
    activity —— 用于启动支付控件的活动对象
    spId —— 保留使用，这里输入null
    sysProvider —— 保留使用，这里输入null
    orderInfo —— 订单信息为交易流水号，即TN，为商户后台从银联后台获取。
    mode —— 银联后台环境标识，“00”将在银联正式环境发起交易,“01”将在银联测试环境发起交易

    返回值：
    UPPayAssistEx.PLUGIN_VALID —— 该终端已经安装控件，并启动控件
    UPPayAssistEx.PLUGIN_NOT_FOUND — 手机终端尚未安装支付控件，需要先安装支付控件
    */

    private void pay() {
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取tn
                new Thread(UnionPayActivity.this).start();
                // TODO: 2016/11/8 获取tn
            }
        });
    }

    /*

        sign —— 签名后做Base64的数据
        data —— 用于签名的原始数据
        data中原始数据结构：
        pay_result —— 支付结果success，fail，cancel
        tn          —— 订单号
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d(TAG, "onActivityResult: data = " + data);
        if (data == null) {
            return;
        }

        Bundle extras = data.getExtras();
        String str = extras.getString("pay_result");

        Log.d(TAG, "onActivityResult: extras = " + extras.toString());//json串
        Log.d(TAG, "onActivityResult: str = " + str);

        if (str.equalsIgnoreCase("success")) {
            // 支付成功后，extra中如果存在result_data，取出校验
            // result_data结构见c）result_data参数说明

            Log.d(TAG, "onActivityResult: data.hasExtra(\"result_data\") = " + data.hasExtra("result_data"));

            Log.d(TAG, "onActivityResult: " + extras.getString("result_data"));
            if (data.hasExtra("result_data")) {
                try {
                    JSONObject resultJson = new JSONObject(str);
                    String s = resultJson.toString();

                    Log.d(TAG, "onActivityResult: sss = " + s);
                    String sign = resultJson.getString("sign");
                    String dataOrg = resultJson.getString("data");
                    // 验签证书同后台验签证书
                    // 此处的verify，商户需送去商户后台做验签
                    boolean ret = verify(dataOrg, sign, mMode);
                    if (ret) {
                        // 验证通过后，显示支付结果
                        showResultDialog(" 支付成功！ ");
                    } else {
                        // 验证不通过后的处理
                        // 建议通过商户后台查询支付结果
                        showResultDialog(" 支付失败！ ");
                    }
                } catch (JSONException e) {
                    //处理异常
                }
            } else {
                // 未收到签名信息
                // 建议通过商户后台查询支付结果

            }
        } else if (str.equalsIgnoreCase("fail")) {
            showResultDialog(" 支付失败！ ");
        } else if (str.equalsIgnoreCase("cancel")) {
            showResultDialog(" 你已取消了本次订单的支付！ ");
        }
    }

    //去后台做验签
    private boolean verify(String sign, String s, String mode) {
        // TODO: 2016/11/8 后台验证
        return true;
    }

    private void showResultDialog(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    private static final String TN_URL_01 = "http://101.231.204.84:8091/sim/getacptn";

    @Override
    public void run() {
        String tn = null;
        InputStream is;
        try {

            String url = TN_URL_01;

            URL myURL = new URL(url);
            URLConnection ucon = myURL.openConnection();
            ucon.setConnectTimeout(120000);
            is = ucon.getInputStream();
            int i = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((i = is.read()) != -1) {
                baos.write(i);
            }

            tn = baos.toString();
            Log.d(TAG, "run: tn = " + tn);
            is.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Message msg = mHandler.obtainMessage();
        msg.obj = tn;
        mHandler.sendMessage(msg);
    }

    public static final String TAG = "UnionPayActivity";

    @Override
    public boolean handleMessage(Message msg) {

        String tn = "";
        Log.d(TAG, "handleMessage: msg.obj = " + msg.obj);
        if (msg.obj == null || ((String) msg.obj).length() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("错误提示");
            builder.setMessage("网络连接失败,请重试!");
            builder.setNegativeButton("确定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.create().show();
        } else {
            tn = (String) msg.obj;
            /*************************************************
             * 步骤2：通过银联工具类启动支付插件
             ************************************************/
            UPPayAssistEx.startPay(this, null, null, tn, mMode);
        }
        return false;
    }

    /*------------------------------------------以上银联支付---------------------------------------------*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, POPUPWINDOW, POPUPWINDOW, "popupWindow");
        menu.add(0, COPY_TEXT, COPY_TEXT, "copy_text");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case POPUPWINDOW:
                showPopWindow();
                break;
            case COPY_TEXT:
                copy();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void copy() {
        String content = mEditText.getText().toString();
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        //  cm.setText(content);//已过时
        cm.setPrimaryClip(ClipData.newPlainText("ClipData", content));
        Toast.makeText(this, "复制成功！", Toast.LENGTH_LONG).show();
    }

    private void showPopWindow() {
        View viewPopScreen = LayoutInflater.from(this).inflate(R.layout.nc_activity_popwindow, null);
        if (popupWindow == null) {
            popupWindow = new PopupWindow(viewPopScreen, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        }
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(this.getResources(), (Bitmap) null));
        popupWindow.update();

        //初始化popwindow的控件
        TextView tvContent = (TextView) viewPopScreen.findViewById(R.id.tvContent);
        StringBuilder sb = new StringBuilder();
        sb.append(
                "1、每人每天最多签到一次，签到后有机会获得积分\n" +
                        "2、网站可根据活动举办的实际情况，在法律允许的范围内，对本活动规则变动或调整。\n" +
                        "3、对不正当手段（包括但不限于作弊、扰乱系统、实施网络攻击等）参与活动的用户，网站有权禁止其参与活动，取消其获奖资格（如奖励已发放，网站有权追回）。\n" +
                        "4、活动期间，如遭遇自然灾害、网络攻击或系统故障等不可抗拒因素导致活动暂停举办，网站无需承担赔偿责任或进行补偿。");
        tvContent.setText(sb.toString());

        Button btnConfirm = (Button) viewPopScreen.findViewById(R.id.btnConfirm);
        FrameLayout flBack = (FrameLayout) viewPopScreen.findViewById(R.id.flBack);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        //设置出现位置
        popupWindow.showAtLocation(viewPopScreen, Gravity.CENTER, 0, 0);

    }
}