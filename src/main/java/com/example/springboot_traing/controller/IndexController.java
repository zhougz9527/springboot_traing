package com.example.springboot_traing.controller;

import com.example.springboot_traing.global.Constants;
import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import com.example.springboot_traing.service.MailService;
import com.example.springboot_traing.service.RedisService;
import com.example.springboot_traing.service.UserService;
import com.example.springboot_traing.utils.CommonUtil;
import com.example.springboot_traing.utils.JWTUtil;
import com.example.springboot_traing.utils.RegexUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    MailService mailService;

    @Autowired
    RedisService redisService;


    @GetMapping(path = "/index")
    public String index() {
        return "hello world";
    }

    @ApiOperation(value = "注册", notes = "用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", required = true, example = "1649563336@qq.com"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true, example = "abc123456"),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", required = true, example = "850549")
    })
    @PostMapping(path = "/register")
    public Result register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "code") String code) {
        if (RegexUtil.isEmail(username) && RegexUtil.isPassword(password) && !StringUtils.isEmpty(code)) {
            return Optional.ofNullable(redisService.get(RedisService.CODE + username)).map(o -> {
                if (code.equals(String.valueOf(o))) {
                    return userService.createUser(username, password) ? ResultUtil.succeedNoData() : ResultUtil.error(201);
                } else {
                    return ResultUtil.error(206);
                }
            }).orElse(ResultUtil.error(206));
        } else {
            return ResultUtil.error(203);
        }
    }


    @ApiOperation(value = "登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", required = true, example = "1649563336@qq.com"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true, example = "abc123456")
    })
    @PostMapping(path = "/login")
    public Result login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        return RegexUtil.isEmail(username) && RegexUtil.isPassword(password) ?
                Optional.ofNullable(userService.checkUserByUsernameAndPassword(username, password)).map(user -> {
                    String token = JWTUtil.generateJWT(Constants.JWT_TTLMILLIS, user);
                    Map<String, String> response = new HashMap<>();
                    if (StringUtils.isEmpty(token)) {
                        return ResultUtil.error(500);
                    }
                    response.put("token", token);
                    return ResultUtil.success(response);
                }).orElse(ResultUtil.error(207)) : ResultUtil.error(203);
    }


    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", required = true, example = "1649563336@qq.com")
    })
    @GetMapping(path = "/sendCode")
    public Result sendCode(@RequestParam(value = "email") String email) {
        return RegexUtil.isEmail(email) ?
                mailService.sendMail(email) ? ResultUtil.succeedNoData() : ResultUtil.error(204) :
                ResultUtil.error(205);
    }


    @ApiOperation(value = "重置密码", notes = "重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String", required = true, example = "1649563336@qq.com"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true, example = "abc123456"),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "String", required = true, example = "12345")
    })
    @PostMapping(path = "/resetPassword")
    public Result resetPassword(@RequestParam(value = "email") String username,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "code") String code) {
        if (RegexUtil.isEmail(username) && RegexUtil.isPassword(password)) {
            return Optional.ofNullable(redisService.get(RedisService.CODE + username)).map(o -> {
                if (code.equals(String.valueOf(o))) {
                    return userService.resetPassword(username, password).map(user -> ResultUtil.succeedNoData()).orElse(ResultUtil.error(208));
                } else {
                    return ResultUtil.error(206);
                }
            }).orElse(ResultUtil.error(206));
        } else {
            return ResultUtil.error(203);
        }
    }


}
