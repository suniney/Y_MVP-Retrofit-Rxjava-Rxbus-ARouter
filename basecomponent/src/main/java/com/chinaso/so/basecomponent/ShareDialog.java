package com.chinaso.so.basecomponent;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chinaso.so.basecomponent.entity.ShareEntity;
import com.chinaso.so.basecomponent.entity.ShareInfoEntity;
import com.chinaso.so.basiclib.utils.CommonUtils;
import com.chinaso.so.basiclib.utils.DisplayUtil;
import com.chinaso.so.basiclib.utils.ToastUtil;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanxu
 * @date:2019/1/24
 * @description:
 */

public class ShareDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private ShareManager mShareManager;
    private ShareInfoEntity mShareEntity;
    private ShareManager.ShareCallback shareCallback;
    private OnShareListener listener;
    private ShareAdapter adapter;

    public void setListener(OnShareListener listener) {
        this.listener = listener;
    }

    public ShareDialog(Context context, ShareInfoEntity entity) {
        super(context);
        this.context = context;
        this.mShareEntity = entity;
        init();
    }

    public interface OnShareListener {
        void onShare(ShareEntity shareEntity);
    }

    public ShareDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ShareDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void init() {
        mShareManager = new ShareManager(CommonUtils.scanForActivity(context));
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.base_dialog_share, null);
        view.setPadding(0, 0, 0, 10);
        setContentView(view);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        ViewGroup.MarginLayoutParams viewParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        viewParams.bottomMargin = DisplayUtil.Dp2Px(context, 8f);
        view.setLayoutParams(viewParams);
        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = DisplayUtil.getScreenW(context) - DisplayUtil.Dp2Px(context, 20); //宽度设置为屏幕
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);

        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this);

        List<ShareEntity> list = new ArrayList();
        list.add(new ShareEntity(R.mipmap.ico_share_qq, Constant.SHARE_QQ));
        list.add(new ShareEntity(R.mipmap.ico_share_qqkj, Constant.SHARE_QQ_SPACE));
        list.add(new ShareEntity(R.mipmap.ico_share_wb, Constant.SHARE_WB));
        list.add(new ShareEntity(R.mipmap.ico_share_wxpyq, Constant.SHARE_WX_FRIENDS));
        list.add(new ShareEntity(R.mipmap.ico_share_wx, Constant.SHARE_WX));
        list.add(new ShareEntity(R.mipmap.ico_share_link, Constant.SHARE_LINK));

        RecyclerView recycler = view.findViewById(R.id.share_recycler);
        GridLayoutManager hotManager = new GridLayoutManager(context, 4);
        ShareAdapter adapter = new ShareAdapter(context, list, new ShareAdapter.ShareAdapterOnClickListener() {
            @Override
            public void onClick(int position) {
                if (listener != null) {
                    listener.onShare(list.get(position));
                }
                switch (list.get(position).getTitle()) {
                    case Constant.SHARE_QQ:
                        mShareManager.startShare(mShareEntity, SHARE_MEDIA.QQ, shareCallback);
                        break;
                    case Constant.SHARE_QQ_SPACE:
                        mShareManager.startShare(mShareEntity, SHARE_MEDIA.QZONE, shareCallback);
                        break;
                    case Constant.SHARE_SZT:
                        break;
                    case Constant.SHARE_WB:
                        mShareManager.startShare(mShareEntity, SHARE_MEDIA.SINA, shareCallback);
                        break;
                    case Constant.SHARE_WX_FRIENDS:
                        mShareManager.startShare(mShareEntity, SHARE_MEDIA.WEIXIN_CIRCLE, shareCallback);
                        break;
                    case Constant.SHARE_WX:
                        mShareManager.startShare(mShareEntity, SHARE_MEDIA.WEIXIN, shareCallback);
                        break;
                    case Constant.SHARE_LINK:
                        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData = ClipData.newPlainText("url", mShareEntity.getTargetUrl());
                        if (clipData.toString() != null) {
                            cmb.setPrimaryClip(clipData);
                            new ToastUtil(context).showToast("复制成功");
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        recycler.setLayoutManager(hotManager);
        recycler.setAdapter(adapter);
        recycler.setNestedScrollingEnabled(false);
        shareCallback = new ShareManager.ShareCallback() {
            @Override
            public void finishShare() {
                dismiss();
            }
        };
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            dismiss();
        } else {
        }
    }

}

