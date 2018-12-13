package com.example.springboot_traing.controller;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.service.IOService;
import com.example.springboot_traing.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    OssService ossService;

    @Autowired
    IOService ioService;

    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", dataType = "File", required = true),
            @ApiImplicitParam(name = "type", value = "文件作用, 1表示头像", dataType = "int", required = true, example = "1")
    })
    @PostMapping(path = "/upload")
    public Result uploadFile(@RequestParam(value = "file") MultipartFile file,
                             @RequestParam(value = "type") int type) {
        // TODO ...
        return null;
    }

}
