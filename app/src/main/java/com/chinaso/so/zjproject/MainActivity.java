package com.chinaso.so.zjproject;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chinaso.so.basiclib.constant.ConstantRouteName;
import com.chinaso.so.basiclib.base.BaseActivity;


@Route(path = ConstantRouteName.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.app_activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        findViewById(R.id.moudle_app_tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getInstance().build(ConstantRouteName.ACTIVITY_LOGIN).navigation();
//                showToast("hahahah");
                ARouter.getInstance().build(ConstantRouteName.ACTIVITY_SHARE).navigation();
                showToast("hahahah");
            }
        });
    }
}
