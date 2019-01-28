package com.chinaso.so.basecomponent;



import com.chinaso.so.basecomponent.entity.ShareInfoEntity;
import com.umeng.socialize.bean.SHARE_MEDIA;


/**
 * @author yanxu
 * @date:2018/10/18
 * @description:
 */
public abstract class BaseShareManager implements ShareInterface {

    @Override
    public void startShare(ShareInfoEntity shareEntity, SHARE_MEDIA platform, ShareManager.ShareCallback shareCallback) {
        if (shareEntity == null) {
            return;
        }
        initShareContent(shareEntity);
        initSharePlatform();
        beginShare(platform, shareCallback);
    }


    /**
     * @param shareEntity
     */
    protected abstract void initShareContent(ShareInfoEntity shareEntity);

    /**
     * 初始化第三方平台
     */
    protected abstract void initSharePlatform();

    /**
     * 开始分享
     */
    protected abstract void beginShare(SHARE_MEDIA platform, ShareManager.ShareCallback shareCallback);
}
