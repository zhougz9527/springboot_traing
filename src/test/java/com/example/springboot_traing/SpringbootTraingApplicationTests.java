package com.example.springboot_traing;

import com.example.springboot_traing.entity.Article;
import com.example.springboot_traing.service.ArticleService;
import com.example.springboot_traing.service.PrettyService;
import com.example.springboot_traing.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;
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

}
