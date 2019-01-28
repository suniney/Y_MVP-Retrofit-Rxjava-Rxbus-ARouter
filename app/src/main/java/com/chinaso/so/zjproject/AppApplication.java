package com.chinaso.so.zjproject;

import com.chinaso.so.basiclib.base.BaseApplication;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * @author yanxu
 * @date:2019/1/15
 * @description:
 */

public class AppApplication extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, "5a12384aa40fa3551f0001d1", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
