package com.example.springboot_traing.entity;

import javax.persistence.Id;

/**
 * @Author: Think
 * @Date: 2018/12/18
 * @Time: 14:53
 */
public class Article {

    @Id
    private String id;
    private String author;
    private String header;
    private String content;
    private long createTime;
    private long updateTime;

    public Article() {
    }

    public Article(String author, String header, String content, long createTime, long updateTime) {
        this.author = author;
        this.header = header;
        this.content = content;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

}
