package com.chinaso.so.basecomponent.http.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UAInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android;lovelycn;1.0");
        return chain.proceed(builder.build());
    }
}
