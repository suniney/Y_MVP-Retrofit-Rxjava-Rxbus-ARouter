package com.chinaso.so.basiclib.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author yanxu
 * @date:2019/1/15
 * @description:
 */

public class BaseApplication extends Application {
    private boolean isDebug = true;
    private static Context yContext;

    @Override
    public void onCreate() {
        super.onCreate();
        yContext = this;
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(BaseApplication.this); // 尽可能早，推荐在Application中初始化
    }

    public static Context getContext() {
        return yContext;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
