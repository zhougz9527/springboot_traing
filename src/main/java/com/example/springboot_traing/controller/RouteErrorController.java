package com.example.springboot_traing.controller;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 路由错误
 *
 * @Author: Think
 * @Date: 2018/12/13
 * @Time: 20:13
 */
@ApiIgnore
@RestController
public class RouteErrorController extends BaseController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public Result error(HttpServletResponse response, HttpServletRequest httpServletRequest) {
        return ResultUtil.error(404);
    }

}
