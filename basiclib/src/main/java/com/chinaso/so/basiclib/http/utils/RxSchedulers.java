package com.chinaso.so.basiclib.http.utils;

/**
 * @author yanxu
 * @date:2019/1/16
 * @description: 通用的Rx线程转换类
 */


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class RxSchedulers {
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return (upstream)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                            @Override
                            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
                                // 用来统一处理Http的resultCode, 并将HttpResult的Data部分剥离出来返回给subscriber
                                return Observable.error(ExceptionConverter.convertException(throwable));
                            }
                        });
            }
        };
    }
}

