package com.example.springboot_traing.config;

import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 22:44
 */
@Component
@Aspect
public class WebLogAspect {

    private static final Logger logger = LogManager.getLogger();

    private ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Pointcut("execution(public * com.example.springboot_traing.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        startTime.set(System.currentTimeMillis());

        //接受请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取请求的参数
        String paramString = "";
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String paramName = enumeration.nextElement();
            paramString += paramName + ": " +request.getParameter(paramName) + " ";
        }

        //记录请求内容
        logger.info("[IP:X-Real-IP:PORT] " + request.getRemoteAddr() + ":" + request.getHeader("X-Real-IP")
                + ":" + request.getRemotePort());
        logger.info("[HTTP_METHOD:URL] " + request.getMethod() + ":" + request.getRequestURL().toString());
        logger.info("[CLASS_METHOD] " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("[ARGS:CONTENT_TYPE] " + paramString + " CONTENT_TYPE: " + request.getContentType());

    }

    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) throws Throwable {
        //处理完请求，返回内容
        logger.info("RESPONSE: " + JSON.toJSONString(result));
        logger.info("SPEND TIME: " + (System.currentTimeMillis() - startTime.get()));
    }

}
