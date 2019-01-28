package com.chinaso.so.basecomponent.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 *
 * @author yanxu
 * @date:2018/10/18
 * @description: dp与px单位间的相互转换
 */

public class DisplayUtil {
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int getScreenW(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowMgr.getDefaultDisplay().getRealMetrics(dm);

        return dm.widthPixels;
    }

    public static int getScreenH(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenHWithoutStateBar(Context context) {
        DisplayMetrics metric = context.getResources().getDisplayMetrics();
        Rect frame = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        // 状态栏高度
        int statusBarHeight = frame.top;
        return metric.heightPixels - statusBarHeight;
    }

}
