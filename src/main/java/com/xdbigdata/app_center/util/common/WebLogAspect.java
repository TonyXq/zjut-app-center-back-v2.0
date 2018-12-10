package com.xdbigdata.app_center.util.common;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by tangyijun on 2017/4/5.
 * good good study,day day up!
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();
//    @Value("${my-config.controller-package}")
//    private String controllerPackage;

    @Pointcut("execution(public * com.xdbigdata.app_center.web..*.*(..))")
    public void webLog(){}

    @Order(1)
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("************URL************ : " + request.getRequestURL().toString());
        log.info("************HTTP_METHOD************ : " + request.getMethod());
        log.info("************IP************ : " + request.getRemoteAddr());
        log.info("************CLASS_METHOD************ : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("************ARGS************ : " + Arrays.toString(joinPoint.getArgs()));
    }

    @Order(1)
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("************RESPONSE************ : " + ret);
        log.info("************SPEND TIME************ :" + (System.currentTimeMillis() - startTime.get()));
    }
}
