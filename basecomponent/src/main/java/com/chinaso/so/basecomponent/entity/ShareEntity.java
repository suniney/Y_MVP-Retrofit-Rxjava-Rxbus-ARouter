package com.chinaso.so.basecomponent.entity;

/**
 * @author: zn
 * created on: 2018/12/6
 * description:
 */
public class ShareEntity {
    private int img;
    private String title;

    public ShareEntity(int img, String title) {
        this.img = img;
        this.title = title;
    }
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
