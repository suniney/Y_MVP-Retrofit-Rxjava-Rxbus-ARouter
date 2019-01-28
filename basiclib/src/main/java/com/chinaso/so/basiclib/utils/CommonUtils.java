package com.chinaso.so.basiclib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/**
 * @author yanxu
 * @date:2019/1/24
 * @description:
 */

public class CommonUtils {
    /**
     * context转activity
     *
     * @param cont
     * @return
     */
    public static Activity scanForActivity(Context cont) {
        if (cont == null) return null;
        else if (cont instanceof Activity) return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());
        return null;
    }
}
