package com.example.springboot_traing.controller;

import com.example.springboot_traing.entity.User;
import com.example.springboot_traing.global.Constants;
import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import com.example.springboot_traing.service.IOService;
import com.example.springboot_traing.service.AliYunOssService;
import com.example.springboot_traing.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Think
 * @Date: 2018/12/13
 * @Time: 23:33
 */
@RestController
@Api(value = "用户相关", tags = {"2"}, description = "用户相关")
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    AliYunOssService ossService;

    @Autowired
    UserService userService;

    @Autowired
    IOService ioService;

    @ApiOperation(value = "上传头像", notes = "上传头像")
    @PostMapping(path = "/uploadAvatar", consumes = "multipart/*", headers = "content-type=multipart/form-date")
    public Result uploadAvatar(@RequestAttribute User user,
                               @ApiParam(value = "上传的文件" ,required = true) MultipartFile file) {
        String fileName = ioService.getFileName(file);
        String ossFilePath = ossService.getFolder(1) + fileName;
        String localFilePath = System.getProperty("user.dir") + File.separator + "tmp";
        String successPath = ioService.download(file, localFilePath, fileName);
        boolean success = ossService.uploadToOss(successPath, ossFilePath);
        ioService.delete(successPath);
        if (success) {
            Map<String, String> response = new HashMap<>();
            String avatar = ossService.getOssUrl(Constants.ALIYUN_BUCKET, ossService.getFolder(1), fileName);
            user.setAvatar(avatar);
            userService.save(user);
            response.put("url", avatar);
            return ResultUtil.success(response);
        } else {
            return ResultUtil.error(209);
        }
    }


    @ApiOperation(value = "登出", notes = "用户登出")
    @PostMapping(path = "/logout")
    public Result logout(@RequestAttribute User user) {
        return userService.deleteUserToken(user.getId()) ? ResultUtil.succeedNoData() : ResultUtil.error(211);
    }

}
