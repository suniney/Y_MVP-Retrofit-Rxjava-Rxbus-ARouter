package com.chinaso.so.basecomponent.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

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
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, "5a12384aa40fa3551f0001d1", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
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
