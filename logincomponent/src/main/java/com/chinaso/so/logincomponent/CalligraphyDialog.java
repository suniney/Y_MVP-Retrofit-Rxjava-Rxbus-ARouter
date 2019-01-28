package com.chinaso.so.logincomponent;

import android.Manifest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chinaso.so.basecomponent.base.BaseApplication;
import com.tbruyelle.rxpermissions2.RxPermissions;


/**
 * @author yanxu
 * @date:2019/1/21
 * @description:
 */

public class CalligraphyDialog extends Dialog {
    private CalligraphyDialogListener calligraphyDialogListener;
    private Activity mContext;
    private TextView mTakeCamera;
    private TextView mTakePhoto;
    private TextView mTakeCancle;

    public CalligraphyDialog(@NonNull Activity context, CalligraphyDialogListener calligraphyDialogListener) {
        super(context);
        this.mContext = context;
        this.calligraphyDialogListener = calligraphyDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.login_dialog_calligraphy, null);
        setContentView(rootView);
        mTakeCamera = (TextView) rootView.findViewById(R.id.register_set_camera);
        mTakePhoto = (TextView) rootView.findViewById(R.id.register_take_photo);
        mTakeCancle = (TextView) rootView.findViewById(R.id.register_set_cancle);
        mTakeCamera.setOnClickListener(new CalligraphyDialogClick());
        mTakePhoto.setOnClickListener(new CalligraphyDialogClick());
        mTakeCancle.setOnClickListener(new CalligraphyDialogClick());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高
        lp.width = (int) (d.widthPixels * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(lp);
    }

    class CalligraphyDialogClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int idName = v.getId();
            if (idName == R.id.register_set_camera) {
                RxPermissions rxPermissions = new RxPermissions(mContext);
                rxPermissions.requestEach(Manifest.permission.CAMERA)
                        .subscribe(permission -> {
                            if (permission.granted) {
                                calligraphyDialogListener.takeCamera();
                                return;
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setMessage("提示打开相机权限");
                                builder.setNegativeButton("暂不", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog1, int which) {

                                    }
                                });
                                builder.setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog1, int which) {
                                        Intent intent = new Intent();
                                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", BaseApplication.getContext().getPackageName(), null);
                                        intent.setData(uri);
                                        mContext.startActivity(intent);
                                    }
                                });
                                builder.show();
                            }


                        });
            } else if (idName == R.id.register_take_photo) {
                calligraphyDialogListener.takePhoto();
            } else if (idName == R.id.register_set_cancle) {
                dismiss();
            }

        }
    }

    public interface CalligraphyDialogListener {
        void takeCamera();

        void takePhoto();
    }
}
