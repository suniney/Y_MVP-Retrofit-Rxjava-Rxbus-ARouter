package com.chinaso.so.basecomponent;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chinaso.so.basecomponent.base.BaseActivity;
import com.chinaso.so.basecomponent.constant.ConstantRouteName;
import com.chinaso.so.basecomponent.entity.ShareInfoEntity;

import com.umeng.socialize.UMShareAPI;


@Route(path = ConstantRouteName.ACTIVITY_SHARE)
public class ShareActivity extends BaseActivity implements View.OnClickListener{
    private ShareDialog shareDialog;

    @Override
    protected int getViewLayout() {
        return R.layout.base_activity_main;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    }

    @Override
    public void onPause() {
        super.onPause();
        if (shareDialog != null) {
            shareDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initView() {
        super.initView();
        TextView moudle_base_text =  findViewById(R.id.moudle_base_text);
        moudle_base_text.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.moudle_base_text) {
            ShareInfoEntity shareInfoEntity = new ShareInfoEntity();
            shareInfoEntity.setDescribe("111100");
            shareInfoEntity.setTitle("11111");
            shareInfoEntity.setTargetUrl("http://www.baidu.com");
            shareInfoEntity.setPicUrl("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=651075057,2410591194&fm=173&app=25&f=JPEG?w=640&h=344&s=17244BA72A931FC6127A692603006013");
            shareDialog = new ShareDialog(this, shareInfoEntity);
            shareDialog.show();

        }
    }
}
