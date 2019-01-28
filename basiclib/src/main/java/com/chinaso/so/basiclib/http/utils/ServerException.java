package com.chinaso.so.basiclib.http.utils;


/**
 * @author yanxu
 * @date:2018/10/18
 * @description:
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
