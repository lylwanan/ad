package com.ad.exception;

public class LogicException extends RuntimeException {
    private String code;
    private String msg;
    private ErrorCodeMsg errorCodeMsg;

    public LogicException(ErrorCodeMsg errorCodeMsg) {
        this.errorCodeMsg = errorCodeMsg;
    }

    public LogicException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorCodeMsg getErrorCodeMsg() {
        return errorCodeMsg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
