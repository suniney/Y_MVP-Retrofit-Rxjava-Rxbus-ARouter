package com.chinaso.so.basecomponent.constant;

/**
 * @author yanxu
 * @date:2019/1/16
 * @description:
 */

public class ConstantHttpCode {
    //http默认状态码
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;

    //自定义网络请求状态码
    public static final int NET_ERROR_CODE = 0x400;
    public static final int SERVER_ERROR_CODE = 0x500;
    public static final int DATA_ERROR_CODE = 0x600;

    public static final String NET_ERROR_MSG = "网络无连接，请检查网络后重试";
    public static final String SERVER_ERROR_MSG = "服务器异常，请稍后再试";
    public static final String DEFAULT_ERROR_MSG = "数据请求失败，请稍后再试";
}
