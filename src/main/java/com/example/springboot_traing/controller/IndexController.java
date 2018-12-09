package com.example.springboot_traing.controller;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import com.example.springboot_traing.service.UserService;
import com.example.springboot_traing.utils.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 22:55
 */
@RestController
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    UserService userService;


    @GetMapping(path = "/index")
    public String index() {
        return "hello world";
    }

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
