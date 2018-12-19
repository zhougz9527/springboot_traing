package com.example.springboot_traing.controller;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import com.example.springboot_traing.service.ArticleService;
import com.example.springboot_traing.service.RedisService;
import com.example.springboot_traing.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.PAData;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Think
 * @Date: 2018/12/18
 * @Time: 16:49
 */
@RestController
@Api(value = "每日相关", tags = {"3"}, description = "每日相关")
@RequestMapping("/daily")
public class DailyController extends BaseController {

    @Autowired
    ArticleService articleService;

    @Autowired
    RedisService redisService;

    @Autowired
    WeatherService weatherService;


    @ApiOperation(value = "每日一文", notes = "每日一文")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "random", value = "是否随机", dataType = "boolean", required = false, example = "true")
    })
    @PostMapping(path = "/article")
    public Result article(@RequestParam(name = "random", defaultValue = "false") boolean random) {
        return articleService.crawlArticle(random).map(article -> {
            String author = article.getAuthor();
            String header = article.getHeader();
            String content = article.getContent();
            if (!articleService.findByHeaderAndAuthor(header, author).isPresent()) {
                articleService.save(author, header, content);
            }
            Map<String, String> response = new HashMap<>();
            response.put("author", author);
            response.put("header", header);
            response.put("content", content);
            return ResultUtil.success(response);
        }).orElse(ResultUtil.error(212));
    }


    @ApiOperation(value = "每日一图", notes = "每日一图")
    @PostMapping(path = "/pretty")
    public Result pretty() {
        return ResultUtil.success(redisService.get(redisService.PRETTY));
    }


    @ApiOperation(value = "每日天气", notes = "每日天气")
    @PostMapping(path = "/weather")
    public Result weather(HttpServletRequest httpServletRequest) {
        Map<String, String> map = weatherService.getIpCity(httpServletRequest.getRemoteAddr());
        return ResultUtil.success(map);
    }

}
