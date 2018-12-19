package com.example.springboot_traing.service;

import com.example.springboot_traing.entity.Article;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/18
 * @Time: 15:09
 */
public interface ArticleService {

    Article save(Article article);

    Article save(String author, String header, String content);

    Optional<Article> findById(int id);

    List<Article> findAll();

    List<Article> findByAuthor(String author);

    List<Article> findByHeader(String header);

    Optional<Article> findByHeaderAndAuthor(String header, String author);

    Optional<Article> crawlArticle(boolean random);
}
