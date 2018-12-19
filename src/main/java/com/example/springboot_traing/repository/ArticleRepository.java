package com.example.springboot_traing.repository;

import com.example.springboot_traing.entity.Article;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/18
 * @Time: 15:05
 */
public interface ArticleRepository extends BaseMongoRepository<Article, Integer> {



    Optional<Article> findById(Integer integer);

    List<Article> findAll();

    List<Article> findByAuthor(String author);

    List<Article> findByHeader(String header);

    Optional<Article> findByHeaderAndAuthor(String header, String author);

}
