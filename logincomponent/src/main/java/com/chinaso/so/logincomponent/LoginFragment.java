package com.chinaso.so.logincomponent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chinaso.so.basecomponent.base.BaseFragment;
import com.chinaso.so.basecomponent.constant.ConstantRouteName;

/**
 * @author yanxu
 * @date:2019/1/28
 * @description:
 */
@Route(path = ConstantRouteName.FRAGMENT_LOGIN)
public class LoginFragment extends BaseFragment {
    @Override
    protected int getViewLayout() {
        return R.layout.login_fragment_my;
    }

}
