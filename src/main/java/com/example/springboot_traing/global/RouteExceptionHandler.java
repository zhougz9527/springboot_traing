package com.example.springboot_traing.global;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Think
 * @Date: 2018/12/10
 * @Time: 15:17
 */
@RestController
public class RouteExceptionHandler implements ErrorController{

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public Result error(HttpServletResponse response, HttpServletRequest httpServletRequest) {
        return ResultUtil.error(404);
    }

}
