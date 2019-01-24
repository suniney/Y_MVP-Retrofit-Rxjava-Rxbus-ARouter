package com.chinaso.so.basiclib.entity;

/**
 * @author yanxu
 * @date:2019/1/16
 * @description: 服务器响应数据的基础实体类
 */

public class BaseResponse<T> {

    private int code;
    private String message;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isSuccess(){
        return code == 0;
    }
}
