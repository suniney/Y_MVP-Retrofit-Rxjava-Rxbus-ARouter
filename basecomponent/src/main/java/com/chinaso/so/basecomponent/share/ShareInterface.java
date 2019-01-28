package com.chinaso.so.basecomponent.share;


import com.chinaso.so.basecomponent.entity.ShareInfoEntity;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * @author yanxu
 * @date:2018/10/18
 * @description:
 */
public interface ShareInterface {

    void startShare(ShareInfoEntity shareEntity, SHARE_MEDIA platform, ShareManager.ShareCallback shareCallback);
}
