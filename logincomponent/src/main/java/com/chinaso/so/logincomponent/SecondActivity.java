package com.chinaso.so.logincomponent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chinaso.so.basecomponent.base.BaseActivity;
import com.chinaso.so.basecomponent.base.RxBus;
import com.chinaso.so.basecomponent.base.RxEvent;
import com.chinaso.so.basecomponent.constant.ConstantRouteName;


/**
 * @author yanxu
 * @date:2019/1/24
 * @description:
 */
@Route(path = ConstantRouteName.ACTIVITY_SECOND)
public class SecondActivity extends BaseActivity {
    @Override
    protected int getViewLayout() {
        return R.layout.login_activity_main;
    }

    @Override
    protected void initView() {
        RxBus.getDefault().post(new RxEvent(123456, "1111"));
        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.fragment_container, new LoginFragment())   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
    }

}
