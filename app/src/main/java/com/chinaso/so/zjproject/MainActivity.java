package com.chinaso.so.zjproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chinaso.so.basecomponent.base.BaseActivity;
import com.chinaso.so.basecomponent.constant.ConstantRouteName;

import butterknife.BindView;
import butterknife.OnClick;


@Route(path = ConstantRouteName.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {
    @BindView(R.id.app_tv_tab_home)
    TextView tv_tab_home;
    @BindView(R.id.app_tv_tab_video)
    TextView tv_tab_video;
    @BindView(R.id.app_tv_tab_atlas)
    TextView tv_tab_atlas;
    @BindView(R.id.app_tv_tab_my)
    TextView tv_tab_my;
    FragmentManager fm;
    Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取Fragment
        Fragment myFragment = (Fragment) ARouter.getInstance().build(ConstantRouteName.FRAGMENT_LOGIN).navigation();
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(ConstantRouteName.FRAGMENT_HOME).navigation();
        if (savedInstanceState == null) {
            mCurrentFragment = myFragment;
            fm = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.app_content, myFragment, "myFragment")
                    .add(R.id.app_content, homeFragment, "homeFragment")
                    .commit();
        } else {
            myFragment = getSupportFragmentManager().findFragmentByTag("myFragment");
            //show()一个即可
            fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .show(myFragment)
                    .hide(homeFragment)
                    .commit();
        }
    }

    @Override
    protected int getViewLayout() {
        return R.layout.app_activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

    }


    @SuppressLint("NewApi")
    private void clearSelection() {
        tv_tab_home.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.mipmap.home_news_normal), null, null);
        tv_tab_video.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.mipmap.home_video_normal), null, null);
        tv_tab_atlas.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.mipmap.home_atlas_normal), null, null);
        tv_tab_my.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.mipmap.home_my_normal), null, null);
        tv_tab_home.setTextColor(ContextCompat.getColor(this, R.color.new_main_color));
        tv_tab_video.setTextColor(ContextCompat.getColor(this, R.color.new_main_color));
        tv_tab_my.setTextColor(ContextCompat.getColor(this, R.color.new_main_color));
        tv_tab_atlas.setTextColor(ContextCompat.getColor(this, R.color.new_main_color));
    }

    public void switchFragment(String toTag) {
        Fragment to = fm.findFragmentByTag(toTag);
        if (mCurrentFragment != to) {
            fm.beginTransaction().hide(mCurrentFragment).show(to).commit();
            mCurrentFragment = to;
        }
    }



    @OnClick({R.id.app_tv_tab_home, R.id.app_tv_tab_video, R.id.app_tv_tab_atlas, R.id.app_tv_tab_my})
    public void onViewClicked(View view) {
        clearSelection();
        switch (view.getId()) {
            case R.id.app_tv_tab_home:
                tv_tab_home.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.mipmap.home_news_pressed), null, null);
                tv_tab_home.setTextColor(ContextCompat.getColor(this, R.color.tabcolor));
                switchFragment("myFragment");
                break;

            case R.id.app_tv_tab_video:
                tv_tab_video.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.mipmap.home_video_pressed), null, null);
                tv_tab_video.setTextColor(ContextCompat.getColor(this, R.color.tabcolor));
                switchFragment("homeFragment");
                break;
            case R.id.app_tv_tab_atlas:
                tv_tab_atlas.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.mipmap.home_atlas_pressed), null, null);
                tv_tab_atlas.setTextColor(ContextCompat.getColor(this, R.color.tabcolor));
                switchFragment("myFragment");
                break;

            case R.id.app_tv_tab_my:
                tv_tab_my.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(this, R.mipmap.home_my_pressed), null, null);
                tv_tab_my.setTextColor(ContextCompat.getColor(this, R.color.tabcolor));
                switchFragment("myFragment");
                break;
        }
    }


}
