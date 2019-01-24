package com.chinaso.so.basiclib.http.utils;


/**
 * @说明 server exception
 * @作者 zhanghe
 * @时间 2017/11/28
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
