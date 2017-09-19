package com.vincyan.ncextensions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        WebView webView = (WebView) findViewById(R.id.webView);
        //解决乱码问题
        webView.loadDataWithBaseURL(null, getIntent().getStringExtra("content"), "text/html", "utf-8", null);
    }
}
