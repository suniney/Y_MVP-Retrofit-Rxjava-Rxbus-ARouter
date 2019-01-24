package com.chinaso.so.logincomponent.net;

import com.chinaso.so.logincomponent.entity.SplashEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author yanxu
 * @date:2019/1/16
 * @description: Service
 */

public interface CommonService {
    @GET("/1/startuppage")
    Observable<List<SplashEntity>> getSplashBkgData();
}
