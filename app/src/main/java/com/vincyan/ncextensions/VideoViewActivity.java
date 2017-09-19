package com.vincyan.ncextensions;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {

    private static final String VIDEO_URL = "<img src=\"http://livehls.cnrmall.com/live/hls/index.m3u8\"/>";
    private String urlStream = "http://livehls.cnrmall.com/live/hls/index.m3u8";
    private VideoView myVideoView;
    private WebView mWebView;
    public static final String TAG = "VideoViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用webview
//        setContentView(R.layout.activity_web_view);
//        mWebView = (WebView) findViewById(R.id.webView);
//        mWebView.loadData(VIDEO_URL, "text/html", "utf-8");
//        Log.d(TAG, "onCreate: VIDEO_URL = " + VIDEO_URL);


        setContentView(R.layout.activity_video_view);
        initVideoView();
    }

    private void initVideoView() {
        myVideoView = (VideoView) this.findViewById(R.id.myVideoView);
        MediaController mc = new MediaController(this);
        myVideoView.setMediaController(mc);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myVideoView.setVideoURI(Uri.parse(urlStream));
                myVideoView.start();
            }
        });
    }
}
