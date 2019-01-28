package com.chinaso.so.basiclib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chinaso.so.basiclib.R;
import com.chinaso.so.basiclib.utils.ToastUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


/**
 * @author yanxu
 * @date:2018/10/18
 * @description:
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements BaseView {
    protected Context mContext;
    protected T mPresenter;
    private ToastUtil mToastUtil;
    private Unbinder unbinder;
    private CompositeDisposable mDisposables;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayout());
        unbinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        //注册eventbus
        Disposable disposable = RxBus.getDefault()
                .register(RxEvent.class, new Consumer<RxEvent>() {
                    @Override
                    public void accept(RxEvent event) {
                        int eventCode = event.getCode();
                        Log.e("RxBus", event.toString());
                        switch (eventCode) {
                            case RxEvent.EVENT_CLOSE_ALL_ACTIVITY:
                                break;
                            default:
                                onEvent(event);
                                break;
                        }
                    }
                });
        addDispose(disposable);

        mContext = this;
        mToastUtil = new ToastUtil(mContext);
        initData();
        initView();
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void onEvent(RxEvent onEvent) {

    }

    protected abstract int getViewLayout();

    /**
     * RxJava 添加订阅
     */
    protected void addDispose(Disposable disposable) {
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        //将所有disposable放入,集中处理
        mDisposables.add(disposable);
    }


    @Override
    public LifecycleTransformer bindLifecycle() {
        //RxAppCompatActivity中的方法bindToLifecycle()
        LifecycleTransformer objectLifecycleTransformer = bindToLifecycle();
        return objectLifecycleTransformer;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        if (mDisposables != null) {
            RxBus.getDefault().unregister(mDisposables);
            mDisposables.clear();
            mDisposables = null;

        }
    }

    protected void showToast(String msg) {
        mToastUtil.showToast(msg);
    }

    protected void showLongToast(String msg) {
        mToastUtil.showToastLong(msg);
    }

}
