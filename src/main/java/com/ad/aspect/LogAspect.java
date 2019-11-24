package com.ad.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author liuyonglang
 * @create on  2019年11月23日23:31:13
 */
//定义一个切面
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    
    private static final String UTF_8 = "utf-8";
    public final String ex = "execution(public * com.ad.controller.*.*(..))";

    // 定义切点Pointcut
    @Pointcut(ex)
    public void excudeService() {
    }

    //执行切点 之前
    @Before("excudeService()")
    public void exBefore(JoinPoint pjp) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();


        String queryString = request.getQueryString();
        String method = request.getMethod();
        Object[] args = pjp.getArgs();
        String params = "";

        try {
            //获取请求参数集合并进行遍历拼接
            if (args.length > 0) {

                if ("POST".equals(method)) {
                    Object object = args[0];
                    params = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
                } else if ("GET".equals(method)) {
                    params = queryString;
                }

                params = URLDecoder.decode(params, UTF_8);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        long startTime = System.currentTimeMillis();
        request.setAttribute("params", params);
        request.setAttribute("startTime", startTime);
    }

    // 通知（环绕）
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        long endTime = System.currentTimeMillis();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String address = request.getRemoteAddr();
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();

        String params = (String) request.getAttribute("params");
        long startTime = (long) request.getAttribute("startTime");

        logger.info("requestMethod:{}  url:{}  address:{}  elapsed:{}ms\nparams:{}\nresponseBody:{}\n",
                method, uri, address, (endTime - startTime), params,
                JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));

        return result;
    }


    //    执行切点之后
    @After("excudeService()")
    public void exAfter(JoinPoint joinPoint) {
    }

}
