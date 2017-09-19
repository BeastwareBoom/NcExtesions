package com.vincyan.ncextensions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.vincyan.ncextensions.bean.Bean;
import com.vincyan.ncextensions.view.MyListView;
import com.vincyan.ncextensions.view.RefreshScrollView;

import java.util.ArrayList;
import java.util.List;

public class ListViewEditTextActivity extends AppCompatActivity {

    private List<Bean> list = new ArrayList<>();
    private MyListView mListView;

    public static final String TAG = ListViewEditTextActivity.class.getSimpleName();
    protected MyAdapter adapter;
    protected RefreshScrollView refreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        mListView = (MyListView) findViewById(R.id.lv);

        refreshView = (RefreshScrollView) findViewById(R.id.refreshView);
        refreshView.setPullLoadEnable(true);
        refreshView.setPullRefreshEnable(true);

        refreshView.setOnPullListener(new RefreshScrollView.OnPullListener() {
            @Override
            public void onRefresh() {
                mListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.add(0, new Bean("刷新"));
                        adapter.notifyDataSetChanged();
                        refreshView.stopRefresh();
                    }
                }, 5000);
            }

            @Override
            public void onLoadMore() {
                mListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.add(new Bean("加载"));
                        adapter.notifyDataSetChanged();
                        refreshView.stopLoadMore();

                        if (mListView.getChildCount() > 25) {
                            refreshView.setLoadAllFinished(true);
                        }
                    }
                }, 5000);
            }
        });

        init();

        adapter = new MyAdapter();
        mListView.setAdapter(adapter);


        //设置滚动监听器
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    if (mark != null) {
                        mark.removeTextChangedListener(mWatcher);
                        Log.d(TAG, "onScrollStateChanged: mark.getTag() = " + mark.getTag());
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            refreshView.stopRefresh();
//            refreshView.stopLoadMore();
//            refreshView.setLoadAllFinished(true);
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }


    private void init() {
        for (int i = 0; i < 100; i++) {
            list.add(new Bean(String.valueOf(i)));
        }
    }

    //使用Field变量
    private MyWatcher mWatcher = new MyWatcher();
    private EditText mark;

    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }


        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(ListViewEditTextActivity.this).inflate(R.layout.item, null);
            }


            final EditText et = (EditText) view.findViewById(R.id.et);
            et.clearFocus();//清除所有edittext的焦点
            final Bean bean = list.get(i);
            et.setText(bean.getMsg());//通过Bean实体类设置数据
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ListViewEditTextActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            });

            et.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    int action = motionEvent.getAction();
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            et.addTextChangedListener(mWatcher);//触摸事件中，为该EditText 添加监听器
                            mWatcher.setPosition(i);//把当前Bean的位置传递到TextWatcher中
                            mark = et;//通过field变量指向当前EditText，并在ListView滚动时清除监听器
                            mark.setTag(i);//调试使用，判断是否为正确的位置
                            break;

                    }
                    return false;
                }
            });
            return view;
        }
    }

    public class MyWatcher implements TextWatcher {

        private int position = -1;//通过该位置指向数据集合中的位置，并用来修改Bean的数据

        @Override
        public void afterTextChanged(Editable editable) {
            Bean bean = list.get(position);
            bean.setMsg(editable.toString());
            Log.d(TAG, "afterTextChanged: position = " + position);
            Log.d(TAG, "afterTextChanged: bean = " + bean);
        }


        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "MyWatcher{" +
                    "position=" + position +
                    '}';
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
    }
}
