package com.ad.exception;

import com.ad.model.ApiResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    private void writeRequestLog(HttpServletRequest request, String result) {
        long endTime = System.currentTimeMillis();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String address = request.getRemoteAddr();

        String params = (String) request.getAttribute("params");
        long startTime = (long) request.getAttribute("startTime");

        logger.info("requestMethod:{}  url:{}  address:{}  elapsed:{}ms\nparams:{}\nresponseBody:{}\n",
                method, uri, address, (endTime - startTime), params, result);
    }

    @ResponseBody
    @ExceptionHandler(LogicException.class)
    public ApiResult handleException(HttpServletRequest request, LogicException exception) {
        ErrorCodeMsg errorCodeMsg = exception.getErrorCodeMsg();
        ApiResult apiResult =  errorCodeMsg == null ?
                new ApiResult(exception.getCode(), exception.getMsg()) :
                new ApiResult(errorCodeMsg.getCode(), errorCodeMsg.getMsg());

        String apiResultString = JSON.toJSONString(apiResult);

        logger.error(apiResultString, exception);
        this.writeRequestLog(request, apiResultString);

        return apiResult;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(HttpServletRequest request, Exception exception) {
        ApiResult apiResult =  new ApiResult(ErrorCodeMsg.SERVICE_EXCEPTION.getCode(),
                ErrorCodeMsg.SERVICE_EXCEPTION.getMsg() + ":" + exception.getMessage());
//        String apiResultString = JSON.toJSONString(apiResult);

//        this.writeRequestLog(request, apiResultString);
//        logger.error(exception.getMessage(), exception);

        return apiResult;
    }

}
