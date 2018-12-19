package com.example.springboot_traing.controller;

import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import com.example.springboot_traing.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
