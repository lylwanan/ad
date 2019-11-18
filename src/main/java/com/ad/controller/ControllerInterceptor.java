package com.ad.controller;

import com.ad.model.ApiResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

//@ControllerAdvice
public class ControllerInterceptor implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public ApiResult beforeBodyWrite(Object body, MethodParameter methodParameter,
                                     MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass,
                                     ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        HttpServletRequest request = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();

        return body instanceof ApiResult ? (ApiResult) body : new ApiResult(body);
    }
}
