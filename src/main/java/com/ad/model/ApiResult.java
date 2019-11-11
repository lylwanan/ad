package com.ad.model;

import com.ad.exception.ErrorCodeMsg;
import lombok.Data;

@Data
public class ApiResult<T> {
    private T data;
    private String code;
    private String msg;
    private long current;

    public ApiResult() {
        this.msg = ErrorCodeMsg.SUCCESS.getMsg();
        this.code = ErrorCodeMsg.SUCCESS.getCode();
        this.current = System.currentTimeMillis();
    }

    public ApiResult(ErrorCodeMsg errorCodeMsg) {
        this();
        this.msg = errorCodeMsg.getMsg();
        this.code = errorCodeMsg.getCode();
    }

    public ApiResult(T data) {
        this();
        this.data = data;
    }

    public ApiResult(String code, String msg) {
        this();
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(String code, String msg, T data) {
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ApiResult<T> SUCCESS(T data) {
        return new ApiResult(data);
    }

    public static ApiResult FAIL() {
        return new ApiResult(ErrorCodeMsg.FAIL.getCode(), ErrorCodeMsg.FAIL.getMsg());
    }

    public static ApiResult ERROR(ErrorCodeMsg errorCodeMsg) {
        return new ApiResult(errorCodeMsg.getCode(), errorCodeMsg.getMsg());
    }
}
