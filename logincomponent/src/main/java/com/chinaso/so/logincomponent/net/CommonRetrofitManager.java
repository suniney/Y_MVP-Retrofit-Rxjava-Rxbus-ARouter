package com.chinaso.so.logincomponent.net;


import com.chinaso.so.basecomponent.http.BaseRetrofitManager;
import com.chinaso.so.basecomponent.http.utils.RxSchedulers;
import com.chinaso.so.logincomponent.entity.SplashEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author yanxu
 * @date:2019/1/16
 * @description:
 */

public class CommonRetrofitManager extends BaseRetrofitManager {
    //超时时间 60s
    private static final int CONNECT_TIME_OUT = 60;

    //设置缓存的大小50M
    private static final int CACHE_MAX = 1024 * 1024 * 50;
    private Retrofit retrofit;
    private CommonService mCommonService;

    public CommonRetrofitManager() {
        mCommonService = getRetrofit().create(CommonService.class);
    }

    private static class SingletonHolder {
        private static final CommonRetrofitManager INSTANCE = new CommonRetrofitManager();
    }

    public static CommonRetrofitManager getInstance() {
        return CommonRetrofitManager.SingletonHolder.INSTANCE;
    }

    /**
     * 刷新token
     */
    public Observable<List<SplashEntity>> getSplashBkgData() {
        return mCommonService.getSplashBkgData().compose(RxSchedulers.<List<SplashEntity>>applySchedulers());
    }


}
