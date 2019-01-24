package com.chinaso.so.basiclib.http.utils;



import com.chinaso.so.basiclib.constant.ConstantHttpCode;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;


/**
 * @说明 网络请求异常处理
 * @作者 zhanghe
 * @时间 2017/11/28
 */
public class ExceptionConverter {

    private static final String TAG = "ExceptionConverter";

    public static Throwable convertException(Throwable e) {
        Throwable finalThrow;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int httpCode = httpException.code();
            switch (httpCode) {
                case ConstantHttpCode.REQUEST_TIMEOUT:
                case ConstantHttpCode.GATEWAY_TIMEOUT:
                case ConstantHttpCode.BAD_GATEWAY:
                    finalThrow = new Throwable(ConstantHttpCode.NET_ERROR_MSG,
                            new Throwable(String.valueOf(ConstantHttpCode.NET_ERROR_CODE)));
                    break;
                case ConstantHttpCode.SERVICE_UNAVAILABLE:
                case ConstantHttpCode.FORBIDDEN:
                case ConstantHttpCode.NOT_FOUND:
                case ConstantHttpCode.UNAUTHORIZED:
                case ConstantHttpCode.INTERNAL_SERVER_ERROR:
                default:
                    finalThrow = new Throwable(ConstantHttpCode.SERVER_ERROR_MSG,
                            new Throwable(String.valueOf(ConstantHttpCode.SERVER_ERROR_CODE)));
                    break;
            }
            return finalThrow;
        } else if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            finalThrow = new Throwable(resultException.getMessage(), new Throwable(resultException.getCode() + ""));
            return finalThrow;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            finalThrow = new Throwable(ConstantHttpCode.SERVER_ERROR_MSG,
                    new Throwable(String.valueOf(ConstantHttpCode.SERVER_ERROR_CODE)));
            return finalThrow;
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException) {
            finalThrow = new Throwable(ConstantHttpCode.NET_ERROR_MSG,
                    new Throwable(String.valueOf(ConstantHttpCode.NET_ERROR_CODE)));
            return finalThrow;
        } else if (e instanceof UnknownHostException) {
            finalThrow = new Throwable(ConstantHttpCode.SERVER_ERROR_MSG,
                    new Throwable(String.valueOf(ConstantHttpCode.SERVER_ERROR_CODE)));
            return finalThrow;
        } else {
            finalThrow = new Throwable(ConstantHttpCode.DEFAULT_ERROR_MSG,
                    new Throwable(String.valueOf(ConstantHttpCode.DATA_ERROR_CODE)));
            return finalThrow;
        }
    }
}
