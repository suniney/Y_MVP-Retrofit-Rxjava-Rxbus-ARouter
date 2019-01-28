package com.chinaso.so.logincomponent.mvp;


import com.chinaso.so.basecomponent.base.BasePresenter;
import com.chinaso.so.basecomponent.base.BaseView;

/**
 * @author yanxu
 * @date:2019/1/23
 * @description:
 */

public class LoginContract {
   public interface View extends BaseView {
        void loginSuccess();

        void loginError();
    }

    public interface Presenter extends BasePresenter {
        void toLogin();
    }
}
