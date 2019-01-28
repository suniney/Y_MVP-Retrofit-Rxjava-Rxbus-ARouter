package com.chinaso.so.basecomponent.entity;


import java.io.Serializable;

/**
 * @说明:
 * @作者: zhanghe
 * @时间: 2017-12-12 15:13
 */
public class ShareInfoEntity implements Serializable {
    private static final long serialVersionUID = -2270460136003014977L;
    //分享图片链接
    private String picUrl;
    //分享标题
    private String title;
    //分享目标链接
    private String targetUrl;
    //分享内容
    private String describe;
    //分享的文章id
    private String docId;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ShareInfoEntity() {
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
