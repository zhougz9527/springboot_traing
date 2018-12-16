package com.example.springboot_traing.controller;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 全局异常
 * @Author: Think
 * @Date: 2018/12/13
 * @Time: 22:19
 */
@ApiIgnore
@RestController
@ControllerAdvice
public class ExceptionController extends BaseController {


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleException(MissingServletRequestParameterException e) {
        logger.info(e.getMessage());
        return ResultUtil.error(203);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        logger.info(e.getMessage());
        return ResultUtil.error(500);
    }

}
