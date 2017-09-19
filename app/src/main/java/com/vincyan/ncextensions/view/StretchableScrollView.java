package com.vincyan.ncextensions.view;

/**
 * Created by vincyan on 2016/10/9.
 */

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

//仿ios可上提下拉的ScrollView
public class StretchableScrollView extends ScrollView {

    private static final String TAG = "StretchableScrollView";

    //移动因子, 是一个百分比, 比如手指移动了100px, 那么View就只移动50px
    //目的是达到一个延迟的效果
    private static final float MOVE_FACTOR = 0.5f;

    //松开手指后, 界面回到正常位置需要的动画时间
    private static final int ANIM_TIME = 100;

    //ScrollView的子View， 也是ScrollView的唯一一个子View
    private View contentView;

    //手指按下时的Y值, 用于在移动时计算移动距离
    //如果按下时不能上拉和下拉， 会在手指移动时更新为当前手指的Y值
    private float startY;

    //用于记录正常的布局位置
    private Rect originalRect = new Rect();

    //在手指滑动的过程中记录是否移动了布局
    private boolean isMoved;
    private boolean marked;//ACTION_MOVE中，滑动到顶部时，记录startY的标记
    private boolean flipped;//ACTION_MOVE中，从顶部到底部的反转标记
    private int scrollStart;//从何处开始滚动
    private Context mContext;
    private int max_scroll;//头部滚动到底部的距离
    private int clearOffset;//消除晃动

    TypedValue tv = new TypedValue();

    public StretchableScrollView(Context context) {
        super(context);
        mContext = context;
    }

    public StretchableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public StretchableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            contentView = getChildAt(0);
        }
    }


    /**
     * 需要将ActionBar考虑在其中，此方式为NoActionBar条件下计算规则
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (contentView == null) return;

        //ScrollView中的唯一子控件的位置信息, 这个位置信息在整个控件的生命周期中保持不变
        originalRect.set(contentView.getLeft(), contentView.getTop(), contentView
                .getRight(), contentView.getBottom());

        int bottom = contentView.getBottom();
        int screenHeight = getScreenHeight(mContext);
        int statusBarHeight = getStatusBarHeight(mContext);
        int navigationBarHeight = getNavigationBarHeight(mContext);
        max_scroll = bottom - screenHeight + statusBarHeight;

        Log.d(TAG, "onLayout: bottom = " + bottom);
        Log.d(TAG, "onLayout: screenHeight = " + screenHeight);
        Log.d(TAG, "onLayout: statusBarHeight = " + statusBarHeight);
        Log.d(TAG, "onLayout: navigationBarHeight = " + navigationBarHeight);
        Log.d(TAG, "onLayout: max_scroll = " + max_scroll);//最大getScrollY=219


        /*
        若有ActionBar
        // Calculate ActionBar height
        int actionBarHeight = 0;
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }

        max_scroll = bottom - screenHeight + statusBarHeight + actionBarHeight;
        Log.d(TAG, "onLayout: actionBarHeight = " + actionBarHeight);
        Log.d(TAG, "onLayout: max_scroll = " + max_scroll);

        */
    }

    //    //在触摸事件中, 处理上拉和下拉的逻辑
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (contentView == null) {
            return super.dispatchTouchEvent(ev);
        }

        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:

                //记录按下时的Y值
                scrollStart = getScrollY();
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "dispatchTouchEvent: MotionEvent.ACTION_MOVE");
                Log.d(TAG, "dispatchTouchEvent: getScrollY = " + getScrollY());
                if (!isMoved) break;  //如果没有移动布局， 则跳过执行

                // 开启动画
                TranslateAnimation anim = new TranslateAnimation(0, 0, contentView.getTop(),
                        originalRect.top);
                anim.setDuration(ANIM_TIME);

                anim.setInterpolator(new FastOutSlowInInterpolator());
                contentView.startAnimation(anim);

                // 设置回到正常的布局位置
                contentView.layout(originalRect.left, originalRect.top,
                        originalRect.right, originalRect.bottom);

                //将标志位设回false
                isMoved = false;
                marked = false;
                flipped = false;
                break;
            case MotionEvent.ACTION_MOVE:

                if (getScrollY() == 0 || getScrollY() == max_scroll) {
                    move(ev);
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    private void move(MotionEvent ev) {
        //第一次记录
        if (!marked) {
            startY = ev.getY();
            marked = true;//从顶点或底部开始记录，且只记录一次；若0和max_scroll都成立，那么会只记录第一次的startY，而这个值不是所需要的
            Log.d(TAG, "dispatchTouchEvent: startY marked-------->startY = " + startY);
        }

        //标记一次完全滑动（开始位置为顶部），解决上面问题
        if (!flipped && Math.abs(getScrollY() - scrollStart) == max_scroll) {
            startY = ev.getY() - clearOffset;//减掉差值，消除晃动
            flipped = true;
            Log.d(TAG, "dispatchTouchEvent: startY flipped=========>>startY = " + startY);
        }

        //计算手指移动的距离
        float nowY = ev.getY();
        int deltaY = (int) (nowY - startY);
        clearOffset = deltaY;//记录

        //是否应该移动布局：下滑到底或上滑到顶
        boolean shouldMove = deltaY != 0;    //可以下拉， 并且手指向下移动  或者可以上拉， 并且手指向上移动; //既可以上拉也可以下拉（这种情况出现在ScrollView包裹的控件比ScrollView还小）
        Log.d(TAG, "dispatchTouchEvent: startY = " + startY);
        Log.d(TAG, "dispatchTouchEvent: nowY = " + nowY);
        Log.d(TAG, "dispatchTouchEvent: deltaY = " + deltaY);
        if (shouldMove) {
            //计算偏移量
            int offset = (int) (deltaY * MOVE_FACTOR);
            //随着手指的移动而移动布局
            contentView.layout(originalRect.left, originalRect.top + offset,
                    originalRect.right, originalRect.bottom + offset);
            isMoved = true;  //记录移动了布局
            Log.d(TAG, "dispatchTouchEvent: offset = " + offset);
        }
    }


    private int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
//        Log.v("dbw", "Status height:" + height);
        return height;
    }

    //获取底部 navigation bar 高度

    private int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
//        Log.v("dbw", "Navi height:" + height);
        return height;
    }

    private int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();

        return display.getHeight();
    }
}
