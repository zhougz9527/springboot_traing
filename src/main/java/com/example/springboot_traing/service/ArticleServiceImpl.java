package com.example.springboot_traing.service;

import com.example.springboot_traing.entity.Article;
import com.example.springboot_traing.global.Constants;
import com.example.springboot_traing.repository.ArticleRepository;
import com.example.springboot_traing.utils.HttpRequestUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/18
 * @Time: 15:11
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article save(String author, String header, String content) {
        Article article = new Article();
        article.setAuthor(author);
        article.setHeader(header);
        article.setContent(content);
        article.setCreateTime(System.currentTimeMillis());
        article.setUpdateTime(System.currentTimeMillis());
        save(article);
        return article;
    }

    @Override
    public Optional<Article> findById(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> findByAuthor(String author) {
        return articleRepository.findByAuthor(author);
    }

    @Override
    public List<Article> findByHeader(String header) {
        return articleRepository.findByHeader(header);
    }

    @Override
    public Optional<Article> findByHeaderAndAuthor(String header, String author) {
        return articleRepository.findByHeaderAndAuthor(header, author);
    }

    @Override
    public Optional<Article> crawlArticle(boolean random) {
        String url = random ? Constants.ARTICLE_URL + "random" : Constants.ARTICLE_URL;
        Map<String, String> headers = new HashMap<>();
        headers.put("Referer", "https://www.google.com.hk/");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        headers.put("X-DevTools-Emulate-Network-Conditions-Client-Id", "5029D206E9CA0EA382EE231E9A595079");
        headers.put("DNT", "1");
        String response = HttpRequestUtil.get(url, headers);
        Document document = Jsoup.parse(response);
        String title = document.title();
        String author = "";
        String header = "";
        if (!StringUtils.isEmpty(title)) {
            String[] strings = title.split("\\|");
            String string = strings[0];
            String[] titles = string.split(" ");
            author = titles[1];
            header = titles[0];
        }
        Elements elements = document.getElementsByClass("article_text");
        String content = "";
        for (Element element : elements) {
            content = element.text();
        }
        Article article = new Article();
        article.setAuthor(author);
        article.setHeader(header);
        article.setContent(content);
        return Optional.of(article);
    }
}
