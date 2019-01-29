package com.chinaso.so.basecomponent.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinaso.so.basecomponent.utils.ToastUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<T extends BasePresenter> extends RxFragment implements BaseView {

    private Unbinder unbinder;
    protected Context mContext;
    private ToastUtil mToastUtil;
    protected T mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(getViewLayout(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        mToastUtil = new ToastUtil(mContext);
        initData();
        initView();
        return mRootView;
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    protected abstract int getViewLayout();


    protected void initView() {
    }

    protected void initData() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    protected void showToast(String msg) {
        mToastUtil.showToast(msg);
    }

    protected void showLongToast(String msg) {
        mToastUtil.showToastLong(msg);
    }

}
