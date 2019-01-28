package com.chinaso.so.basecomponent.utils;

import android.util.Log;

import com.chinaso.so.basecomponent.BuildConfig;


/**
 * @author yanxu
 * @date:2018/10/18
 * @description:
 */

public final class LogUtil {
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private LogUtil() {

    }

    public static void e(final String tag, final String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(final String tag, final String msg, final Exception e) {
        if (DEBUG) {
            Log.e(tag, msg, e);
        }
    }

    public static void v(final String tag, final String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void i(final String tag, final String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void d(final String tag, final String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }
}
