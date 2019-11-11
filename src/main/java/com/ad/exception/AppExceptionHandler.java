package com.ad.exception;

import com.ad.model.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(LogicException.class)
    public ApiResult handleException(HttpServletRequest request, LogicException exception) {
        ErrorCodeMsg errorCodeMsg = exception.getErrorCodeMsg();
        return errorCodeMsg == null ?
                new ApiResult(exception.getCode(), exception.getMsg()) :
                new ApiResult(errorCodeMsg.getCode(), errorCodeMsg.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(HttpServletRequest request, Exception exception) {
        return new ApiResult(ErrorCodeMsg.SERVICE_EXCEPTION.getCode(),
                ErrorCodeMsg.SERVICE_EXCEPTION.getMsg() + ":" + exception.getMessage());
    }

}
