package com.chinaso.so.logincomponent;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chinaso.so.basiclib.base.BaseActivity;
import com.chinaso.so.basiclib.base.RxEvent;
import com.chinaso.so.basiclib.constant.ConstantRouteName;
import com.chinaso.so.basiclib.utils.UploadPicturesHelper;
import com.chinaso.so.logincomponent.mvp.LoginContract;
import com.chinaso.so.logincomponent.mvp.LoginPresenterImpl;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ConstantRouteName.ACTIVITY_LOGIN)
public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {
    @BindView(R2.id.moudle_login_tv_main)
    TextView moudleLoginTvMain;
    private UploadPicturesHelper uploadPicturesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new LoginPresenterImpl(this);
        mPresenter.toLogin();

    }

    @Override
    protected int getViewLayout() {
        return R.layout.login_activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        uploadPicturesHelper = new UploadPicturesHelper(this, "11.jpg", new UploadPicturesHelper.ImageSelectorCallBack() {
            @Override
            public void onSelected(File imgFile) {
                showToast(imgFile.toString());
            }
        });
    }

    @Override
    protected void onEvent(RxEvent onEvent) {
        moudleLoginTvMain.setText(onEvent.getData().toString());
        super.onEvent(onEvent);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError() {

    }

    @OnClick(R2.id.moudle_login_btn_show)
    public void onViewClicked() {
//        Dialog dialog = new CalligraphyDialog(this, new CalligraphyDialog.CalligraphyDialogListener() {
//            @Override
//            public void takeCamera() {
//                uploadPicturesHelper.uploadAvatarFromCameraRequest();
//            }
//
//            @Override
//            public void takePhoto() {
//                uploadPicturesHelper.uploadAvatarFromAlbumRequest();
//            }
//        });
//        dialog.show();
//        startActivity(new Intent(this, SecondActivity.class));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uploadPicturesHelper.onActivityResult(requestCode, resultCode, data);
    }
}
