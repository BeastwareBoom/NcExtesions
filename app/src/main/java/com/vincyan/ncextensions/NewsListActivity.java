package com.vincyan.ncextensions;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vincyan.ncextensions.bean.News;
import com.vincyan.ncextensions.util.LoadImage;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class NewsListActivity extends BaseActivity {

    public static final String URL = "http://www.jiahetianlang.com/app/news_home.php";
    public static final String IMAGE_URL_BASE = "http://www.jiahetianlang.com";
    public static final String TAG = "NewsListActivity";
    @Bind(R.id.iv)
    ImageView iv;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.linearLayout)
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        OkHttpUtils
                .get()
                .url(URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Log.d(TAG, "onError: e = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String data, int code) {
//                        parseJSON(data, listView);
                        List<News> newsList = new Gson().fromJson(data, new TypeToken<ArrayList<News>>() {
                        }.getType());

                        initAnim(newsList);
                    }

                });


    }

    private Handler handler = new Handler();

    private int curremtIndex;

    /**
     * 显示轮播
     *
     * @param newsList
     */
    private void initAnim(final List<News> newsList) {
        final int height = linearLayout.getMeasuredHeight();
        Log.d(TAG, "initAnim: height = " + height);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                curremtIndex++;
                if (curremtIndex > newsList.size() - 1) {
                    curremtIndex = 0;
                }

                tv.setText(newsList.get(curremtIndex).getTitle());
                LoadImage.loadRemoteImg(NewsListActivity.this, iv, IMAGE_URL_BASE + newsList.get(curremtIndex).getCover_photo());


                ObjectAnimator translationY = ObjectAnimator.ofFloat(
                        linearLayout, "translationY",
                        height, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -height)
                        .setDuration(5000);
                translationY.start();

                handler.postDelayed(this, 5000);
            }
        };

        handler.postDelayed(runnable, 0);
    }


    /**
     * jsonObject解析
     *
     * @param data
     */
    private void parseJSON(String data) {
        try {
            List<News> newsList = new ArrayList<News>();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String content = jsonObject.getString("content");
                String title = jsonObject.getString("title");
                String cover_photo = jsonObject.getString("cover_photo");
                String news_from = jsonObject.getString("news_from");
                String id = jsonObject.getString("id");
                String type_id = jsonObject.getString("type_id");
                String add_time = jsonObject.getString("add_time");

                News news = new News();
                news.setId(id);
                news.setAdd_time(add_time);
                news.setContent(content);
                news.setTitle(title);
                news.setType_id(type_id);
                news.setCover_photo(cover_photo);
                news.setNews_from(news_from);
                newsList.add(news);

                Log.d(TAG, "response: content = " + content);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
