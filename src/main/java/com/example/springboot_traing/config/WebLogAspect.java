package com.example.springboot_traing.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 22:44
 */
@Component
@Aspect
public class WebLogAspect {

    private static final Logger logger = LogManager.getLogger();

    @Pointcut("execution(public * com.example.springboot_traing.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore() throws Throwable {

    }

    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) throws Throwable {

    }

}
