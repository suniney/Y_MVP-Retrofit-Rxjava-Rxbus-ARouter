package com.chinaso.so.logincomponent.mvp;

import com.chinaso.so.basecomponent.base.BasePresenterImpl;
import com.chinaso.so.basecomponent.utils.LogUtil;
import com.chinaso.so.logincomponent.entity.SplashEntity;
import com.chinaso.so.logincomponent.net.CommonRetrofitManager;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author yanxu
 * @date:2019/1/23
 * @description:
 */

public class LoginPresenterImpl extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    public LoginPresenterImpl(LoginContract.View view) {
        super(view);
    }

    @Override
    public void toLogin() {
        CommonRetrofitManager.getInstance().getSplashBkgData()
                .compose(mView.bindLifecycle())
                .subscribe(new Consumer<List<SplashEntity>>() {
                    @Override
                    public void accept(List<SplashEntity> splashEntities) throws Exception {
                        LogUtil.e("yanxu", "");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("yanxu", "");
                    }
                });
    }
}
