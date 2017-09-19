package com.vincyan.ncextensions.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/12 14:49
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description 屏幕尺寸工具类
 */
public class ScreenUtil {

    /**
     * 获取屏幕尺寸:x宽度，y高度
     *
     * @param context
     * @return
     */
    public static Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return new Point(dm.widthPixels, dm.heightPixels);
    }

    /**
     * 获取屏幕密度
     *
     * @param context
     * @return
     */
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取屏幕密度DPI
     * 560dpi=160*density
     * <p>
     * 3.5的density和1440, 2392对应360*598
     *
     * @param context
     * @return
     */
    public static int getScreenDensityDPI(Context context) {
        //density*160=densityDpi
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    /**
     * px2dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        return (int) (pxValue / getScreenDensity(context) + 0.5f);
    }

    /**
     * dip2px
     * float 类型的 4.1 和4.9 强转成int类型后，会失去精准度变成 int类型的4，
     * 而如果想四舍五入的话，把他们都加上0.5f
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        return (int) (dipValue * getScreenDensity(context) + 0.5f);
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    //获取底部 navigation bar 高度
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
