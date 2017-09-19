package com.vincyan.ncextensions.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.vincyan.ncextensions.R;
import com.vintage.googleprogressbar.FoldingCirclesDrawable;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/6/9 15:12
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description 下拉刷新，点击尾部加载更多；
 * 使用方式：
 * 1、和普通ScrollView使用相同，包裹一个LinearLayout组件（必须VERTICAL布局）；
 * 2、在LinearLayout中包含一个普通ViewGroup（LinearLayout，RelativeLayout等）
 * 或者包含一个ListView或者RecyclerView使用，需要重写onMeasure的高度
 */
public class RefreshScrollView extends ScrollView {

    public static final String TAG = RefreshScrollView.class.getSimpleName();

    /***********加载更多***********/
    private View footerView;
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    //是否开启上拉加载更多，默认关闭
    private boolean pullLoadEnable;
    //是否全部加载完成
    private boolean loadAllFinished;
    /***********加载更多***********/


    /***********下拉刷新***********/
    //移动距离比，产生与滑动距离非一比一的效果
    private static final float MOVE_FACTOR = 0.4f;
    private LinearLayout.LayoutParams layoutParams;
    private View headerView;
    private View headerImageView;
    private ProgressBar headerProgressBar;
    private TextView headerTextView;
    private int headerViewheight;
    private float refreshStartY;//下拉刷新记录起始位置
    private float maxY;//记录最大下拉值
    private RotateAnimation rotateDownAnim;
    private RotateAnimation rotateUpAnim;
    private static final int ROTATE_ANIM_DURATION = 180;

    private int state;//下拉刷新的状态
    private final static int STATE_NORMAL = 0;
    private final static int STATE_READY = 1;
    private final static int STATE_REFRESHING = 2;

    //是否开启下拉刷新，默认开启
    private boolean pullRefreshEnable = true;
    private boolean smoothScroll;//上拉过程中有回弹时，是否需要滚动到顶部
    private boolean layouting;//是否正在对headerView进行布局

    /***********下拉刷新***********/

    public RefreshScrollView(Context context) {
        this(context, null);
    }

    public RefreshScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        //初始化刷新头部
        initHeader(context);
        //初始化加载更多尾部
        initFooter(context);

    }

    private void initFooter(Context context) {
        footerView = LayoutInflater.from(context).inflate(R.layout.xlistview_footer, null);
        if (!pullLoadEnable)
            footerView.setVisibility(GONE);

        footerProgressBar = (ProgressBar) footerView.findViewById(R.id.xlistview_footer_progressbar);
        footerProgressBar.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(context)
                .build());
        footerTextView = (TextView) footerView.findViewById(R.id.xlistview_footer_hint_textview);
        footerView.findViewById(R.id.xlistview_footer_content).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPullListener != null && !loadAllFinished) {
                    onPullListener.onLoadMore();
                    footerTextView.setText("加载中");
                    footerProgressBar.setVisibility(VISIBLE);
                }
            }
        });
    }

    private void initHeader(Context context) {
        headerView = LayoutInflater.from(context).inflate(R.layout.xlistview_header, null);
        headerImageView = headerView.findViewById(R.id.xlistview_header_arrow);
        headerTextView = (TextView) headerView.findViewById(R.id.xlistview_header_hint_textview);
        headerProgressBar = (ProgressBar) headerView.findViewById(R.id.xlistview_header_progressbar);

        /**
         * FoldingCirclesDrawable
         * NexusRotationCrossDrawable
         * GoogleMusicDicesDrawable
         * ChromeFloatingCircles
         */
        headerProgressBar.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(context)
                .build());

        rotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        rotateUpAnim.setFillAfter(true);

        rotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        rotateDownAnim.setFillAfter(true);
    }

    private void setHeaderViewState(int state) {

        //状态未改变则return
        if (state == this.state) return;

        if (state == STATE_REFRESHING) {
            // 显示进度
            headerImageView.clearAnimation();
            headerImageView.setVisibility(View.INVISIBLE);
            headerProgressBar.setVisibility(View.VISIBLE);
        } else {
            // 显示箭头图片
            headerImageView.setVisibility(View.VISIBLE);
            headerProgressBar.setVisibility(View.INVISIBLE);
        }

        switch (state) {
            case STATE_NORMAL:
                if (this.state == STATE_READY) {
                    headerImageView.startAnimation(rotateDownAnim);
                }
                if (this.state == STATE_REFRESHING) {
                    headerImageView.clearAnimation();
                }
                headerTextView.setText(R.string.xlistview_header_hint_normal);
                break;
            case STATE_READY:
                if (this.state != STATE_READY) {
                    headerImageView.clearAnimation();
                    headerImageView.startAnimation(rotateUpAnim);
                    headerTextView.setText(R.string.xlistview_header_hint_ready);
                }
                break;
            case STATE_REFRESHING:
                headerTextView.setText(R.string.xlistview_header_hint_loading);
                if (onPullListener != null) {
                    onPullListener.onRefresh();
                }
                break;
            default:
        }

        this.state = state;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View content = getChildAt(0);
        if (!(content instanceof LinearLayout)) {
            throw new RuntimeException("RefreshView's child must be LinearLayout");
        }
        //唯一包裹的子View
        LinearLayout contentView = (LinearLayout) content;
        int orientation = contentView.getOrientation();

        //竖直方向布局
        if (orientation != LinearLayout.VERTICAL) {
            throw new RuntimeException("RefreshView's child LinearLayout orientation must be LinearLayout.VERTICAL");
        }

        //为HeaderView设置一个负值的margin使其不可见
        layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headerViewheight = (int) getResources().getDimension(R.dimen.lv_refresh_header_view_height);
        layoutParams.setMargins(0, -headerViewheight, 0, 0);
        headerView.setLayoutParams(layoutParams);

        //headerView添加到第一个位置
        contentView.addView(headerView, 0);
        //footerView添加到最后
        contentView.addView(footerView);
    }

    /**
     * 重置headerView高度（隐藏）
     */
    private void resetHeaderView() {

        layoutParams.setMargins(0, -headerViewheight, 0, 0);
        headerView.setLayoutParams(layoutParams);
        //则滚动至顶部
        if (smoothScroll)
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    smoothScrollTo(0, 0);
                    smoothScroll = false;
                }
            }, 0);
        setHeaderViewState(STATE_NORMAL);
    }

    /**
     * 在dispatchTouchEvent中捕获所有触摸事件
     * Point(1440, 2392)
     * headerViewheight(210)
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果未开启下拉刷新或者正在刷新，则返回
        if (!pullRefreshEnable || state == STATE_REFRESHING)
            return super.dispatchTouchEvent(ev);

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_MOVE:
                //在最顶部时候记录refreshStartY，并修改标记
                if (getScrollY() == 0 && !layouting) {
                    refreshStartY = ev.getRawY();
                    layouting = true;
                }
                float moveY = ev.getRawY();
                //滑动真实距离
                float originDeltaY = moveY - refreshStartY;
                //margin计算距离
                float deltaY = originDeltaY * MOVE_FACTOR;
                int marginTop = (int) (-headerViewheight + deltaY);

                //记录下最大的下拉值
                maxY = maxY >= originDeltaY ? maxY : originDeltaY;
                Log.d(TAG, "dispatchTouchEvent: maxY = " + maxY + " , originDeltaY = " + originDeltaY);

                //下拉刷新（位于顶部且滑动方向向下）
                if (getScrollY() == 0 && deltaY > 0) {
                    Log.d(TAG, "dispatchTouchEvent: refreshStartY = " + refreshStartY + " , moveY = " + moveY + " , deltaY = " + deltaY);
                    //headerView全部显示出时
                    if (deltaY >= headerViewheight) {
                        setHeaderViewState(STATE_READY);
                    } else {
                        setHeaderViewState(STATE_NORMAL);
                    }
                    layoutParams.setMargins(0, marginTop, 0, 0);
                    headerView.setLayoutParams(layoutParams);
                    //头布局已经显示，且正在发生回弹
                } else if (layouting && originDeltaY > 0 && originDeltaY < maxY) {
                    //originDeltaY > 0不是直接向上的滚动，originDeltaY < maxY开始向上回拉
                    setHeaderViewState(STATE_NORMAL);
                    smoothScroll = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (this.state == STATE_READY) {
                    setHeaderViewState(STATE_REFRESHING);
                } else {
                    resetHeaderView();
                }
                layouting = false;
                maxY = 0;//重置
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    //是否全部加载完成
    public void setLoadAllFinished(boolean loadAllFinished) {
        this.loadAllFinished = loadAllFinished;
        footerTextView.setText("全部加载完成");
        footerProgressBar.setVisibility(GONE);
    }

    //开启加载更多
    public void setPullLoadEnable(boolean pullLoadEnable) {
        this.pullLoadEnable = pullLoadEnable;
        if (pullLoadEnable) footerView.setVisibility(VISIBLE);
        else footerView.setVisibility(GONE);
    }

    //停止加载更多
    public void stopLoadMore() {
        footerTextView.setText("点击加载更多");
        footerProgressBar.setVisibility(GONE);
    }

    //开启下拉刷新
    public void setPullRefreshEnable(boolean pullRefreshEnable) {
        this.pullRefreshEnable = pullRefreshEnable;
    }

    //停止刷新
    public void stopRefresh() {
        if (state == STATE_REFRESHING) {
            resetHeaderView();
        }
    }

    /**
     * 商品详情页重写ScrollView，监听滚动到底部
     * 滚动到底部时，clampedY变为true，其余情况为false，通过回调将状态传出去
     */

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        //滑动到底部
        if (clampedY && scrollY != 0)
            Log.d(TAG, "onOverScrolled: ScrollView.FOCUS_DOWN");
        //滑动到顶部
        if (/*clampedY &&*/ scrollY == 0)
            Log.d(TAG, "onOverScrolled: ScrollView.FOCUS_UP");
        if (onFullScrollListener != null) {
            onFullScrollListener.onFullScroll(/*clampedY && */scrollY == 0, clampedY && scrollY != 0);
        }
    }

    private OnFullScrollListener onFullScrollListener;

    public void setOnFullScrollListener(OnFullScrollListener onFullScrollListener) {
        this.onFullScrollListener = onFullScrollListener;
    }

    //监听是否滚动到底部或顶部
    public interface OnFullScrollListener {
        void onFullScroll(boolean top, boolean bottom);
    }

    private OnPullListener onPullListener;

    public void setOnPullListener(OnPullListener onPullListener) {
        this.onPullListener = onPullListener;
    }

    //上拉和下拉的监听器
    public interface OnPullListener {
        //下拉刷新
        void onRefresh();

        //点击加载更多
        void onLoadMore();
    }

}
