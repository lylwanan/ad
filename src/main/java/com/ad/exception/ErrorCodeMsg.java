package com.ad.exception;

import com.ad.exception.ErrorCode;

public enum ErrorCodeMsg {
    FAIL(ErrorCode.FAIL, "请求失败"),
    SUCCESS(ErrorCode.SUCCESS, "请求成功"),
    NOT_FOUND_AD(ErrorCode.NOT_FOUND_AD, "没有请求到广告"),
    SERVICE_EXCEPTION(ErrorCode.SERVICE_EXCEPTION, "服务端异常");

    private String code;
    private String msg;

    ErrorCodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
