package com.chinaso.so.basecomponent.http;

import com.chinaso.so.basecomponent.base.BaseApplication;
import com.chinaso.so.basecomponent.constant.ConstantUrl;
import com.chinaso.so.basecomponent.http.interceptor.HttpCacheInterceptor;
import com.chinaso.so.basecomponent.http.interceptor.LogInterceptor;
import com.chinaso.so.basecomponent.http.interceptor.UAInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author yanxu
 * @date:2019/1/16
 * @description:
 */

public class BaseRetrofitManager {
    //超时时间 60s
    private static final int CONNECT_TIME_OUT = 60;

    //设置缓存的大小50M
    private static final int CACHE_MAX = 1024 * 1024 * 50;
    private Retrofit retrofit;

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(BaseApplication.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, CACHE_MAX);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(CONNECT_TIME_OUT,TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new UAInterceptor())
                .addInterceptor(new LogInterceptor())
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .cache(cache)
                .build();
        return okHttpClient;
    }

    protected Retrofit getRetrofit() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ConstantUrl.BASE_URL)
                .build();
        return retrofit;
    }
}
