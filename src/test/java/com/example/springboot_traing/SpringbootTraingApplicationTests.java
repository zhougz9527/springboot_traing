package com.example.springboot_traing;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot_traing.entity.Article;
import com.example.springboot_traing.service.ArticleService;
import com.example.springboot_traing.service.CityService;
import com.example.springboot_traing.service.PrettyService;
import com.example.springboot_traing.service.RedisService;
import com.example.springboot_traing.utils.HttpRequestUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTraingApplicationTests {

    @Autowired
    ArticleService articleService;

    @Autowired
    PrettyService prettyService;

    @Autowired
    RedisService redisService;

    @Autowired
    CityService cityService;

	@Test
	public void contextLoads() {

	}

	@Test
	public void articleTest() {
        Article article = new Article();
        article.setAuthor("author test");
        article.setHeader("header test");
        article.setContent("content test");
        article.setCreateTime(System.currentTimeMillis());
        article.setUpdateTime(System.currentTimeMillis());
        Article article1 = articleService.save(article);
        System.out.println(article1.getId());
    }

    @Test
    public void crawlPrettyTest() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            List<String> urls = prettyService.crawlPretty(i);
            urls.forEach(System.out::println);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void pushCityToMysql() throws IOException {
        File file = new File("F:\\_city.json");
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONArray jsonArray = JSON.parseArray(content);
        jsonArray.forEach(json -> {
            JSONObject jsonObject = (JSONObject) json;
            int pid = jsonObject.getInteger("pid");
            String name = jsonObject.getString("city_name");
            String code = jsonObject.getString("city_code");
            cityService.save(pid, name, code);
            System.out.println("pid: " + pid + ", name: " + name + ", code: " + code);
        });
    }

    @Test
    public void streamTest() {
	    List<String> stringList = new ArrayList<>();
	    stringList.add("a");
	    stringList.add("b");
	    stringList.add("c");
    }

}
