package com.example.springboot_traing.controller;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Think
 * @Date: 2019/1/21
 * @Time: 16:28
 */
@RestController
@Api(value = "测试controller", tags = {"9"}, description = "测试controller")
@RequestMapping("/test")
public class TestController extends BaseController {


    @ApiOperation(value = "测试session", notes = "测试session")
    @GetMapping(path = "/session")
    public Result session(HttpServletRequest request) {
        request.getSession().setAttribute("username", "jinmu");
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        return ResultUtil.success(map);
    }

    @ApiOperation(value = "获取session", notes = "获取session")
    @GetMapping(path = "getSession")
    public Result getSession(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        return ResultUtil.success(username);
    }
}
