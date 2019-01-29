package com.chinaso.so.homecomponent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chinaso.so.basecomponent.base.BaseFragment;
import com.chinaso.so.basecomponent.constant.ConstantRouteName;


/**
 * @author yanxu
 * @date:2019/1/28
 * @description:
 */
@Route(path = ConstantRouteName.FRAGMENT_HOME)
public class HomeFragment extends BaseFragment {
    @Override
    protected int getViewLayout() {
        return R.layout.home_activity_main;
    }

}
