package com.example.springboot_traing.global;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Think
 * @Date: 2018/12/10
 * @Time: 15:20
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result defaultExceptionHandler(HttpServletRequest request, Exception e) {
        return ResultUtil.error(203);
    }

}
