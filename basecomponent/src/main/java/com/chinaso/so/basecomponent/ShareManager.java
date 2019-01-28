package com.chinaso.so.basecomponent;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import com.chinaso.so.basecomponent.entity.ShareInfoEntity;
import com.chinaso.so.basiclib.base.BaseApplication;
import com.chinaso.so.basiclib.utils.AppUtils;
import com.chinaso.so.basiclib.utils.ToastUtil;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;


/**
 * @author yanxu
 * @date:2018/10/18
 * @description:
 */
public class ShareManager extends BaseShareManager {

    private Activity mContext;

    private UMImage mShareImage;
    private String mTitle;
    private String mDescribe;
    private String mTargetUrl;

    private UMWeb mMedia;
    private String mDocId = "";

    private UMShareAPI mShareAPI;
    private ShareCallback callback;

    public ShareManager(Activity context) {
        mContext = context;
        mShareAPI = UMShareAPI.get(context);
    }

    @Override
    protected void initShareContent(ShareInfoEntity shareEntity) {
        mDocId = shareEntity.getDocId();
        String title = shareEntity.getTitle();
        mTitle = TextUtils.isEmpty(title) ? AppUtils.getAppName(BaseApplication.getContext()) : title;
        String describe = shareEntity.getDescribe();
        mDescribe = TextUtils.isEmpty(title) ? AppUtils.getAppName(BaseApplication.getContext()) : describe;
        String imgUrl = shareEntity.getPicUrl();
        UMImage img = new UMImage(mContext, imgUrl);
        UMImage icon = new UMImage(mContext, BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher));
        mShareImage = TextUtils.isEmpty(imgUrl) ? icon : img;
        mTargetUrl = shareEntity.getTargetUrl();
    }

    /**
     * 设置分享内容
     */
    @Override
    protected void initSharePlatform() {
        mMedia = new UMWeb(mTargetUrl);
        mMedia.setTitle(mTitle);
        mMedia.setDescription(mDescribe);
        mMedia.setThumb(mShareImage);
    }

    @Override
    protected void beginShare(SHARE_MEDIA platform, ShareManager.ShareCallback shareCallback) {
        this.callback = shareCallback;
        if (platform == SHARE_MEDIA.QQ || platform == SHARE_MEDIA.QZONE) {
            if (!mShareAPI.isInstall(mContext, platform)) {
                new ToastUtil(mContext).showToast("请安装QQ客户端");
                return;
            }

        } else if (platform == SHARE_MEDIA.WEIXIN || platform == SHARE_MEDIA.WEIXIN_CIRCLE) {
            if (!mShareAPI.isInstall(mContext, platform)) {
                new ToastUtil(mContext).showToast("请安装微信客户端");
                return;
            }

        } else if (platform == SHARE_MEDIA.SINA) {
            if (!mShareAPI.isInstall(mContext, platform)) {
                new ToastUtil(mContext).showToast("请安装微博客户端");
                return;
            }
        }
        new ShareAction(mContext)
                .setPlatform(platform)
                .withMedia(mMedia)
                .setCallback(umShareListener)
                .share();
        if (TextUtils.isEmpty(mDocId)) {
            return;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Log.e("zhanghe", "share start");
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {//分享成功
            callback.finishShare();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {//分享失败
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {//分享取消

        }
    };


    public interface ShareCallback {
        void finishShare();
    }

}
