package com.example.springboot_traing;

import com.example.springboot_traing.entity.Article;
import com.example.springboot_traing.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTraingApplicationTests {

    @Autowired
    ArticleService articleService;

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

}
