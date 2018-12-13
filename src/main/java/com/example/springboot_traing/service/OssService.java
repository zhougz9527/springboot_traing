package com.example.springboot_traing.service;

/**
 * @Author: Think
 * @Date: 2018/12/13
 * @Time: 23:36
 */
public interface OssService {
    static String AVATAR = "avatar";

    boolean uploadToQiniu(String filePath, String fileName);

}

