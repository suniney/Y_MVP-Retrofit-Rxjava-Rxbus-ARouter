package com.chinaso.so.basiclib.base;


public class BasePresenterImpl<T extends BaseView> implements BasePresenter {
    protected T mView;

    public BasePresenterImpl(T view) {
        mView = view;
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

}
