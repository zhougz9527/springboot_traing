package com.example.springboot_traing.controller;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import com.example.springboot_traing.service.UserService;
import com.example.springboot_traing.utils.RegexUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 22:55
 */
@Api(value = "用户登录注册", tags = {"1"}, description = "用户登录注册")
@RestController
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    UserService userService;


    @GetMapping(path = "/index")
    public String index() {
        return "hello world";
    }

    @ApiOperation(value = "注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", required = true, example = "1649563336@qq.com"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true, example = "123456"),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", required = true, example = "1234")
    })
    @PostMapping(path = "/register")
    public Result register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "code") String code) {
        if (RegexUtil.isEmail(username) && RegexUtil.isPassword(password) && !StringUtils.isEmpty(code)) {
            if (userService.createUser(username, password)) {
                return ResultUtil.succeedNoData();
            } else {
                return ResultUtil.error(201);
            }
        } else {
            return ResultUtil.error(203);
        }
    }


}
