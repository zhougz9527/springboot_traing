package com.example.springboot_traing.service;

/**
 * @Author: Think
 * @Date: 2018/12/13
 * @Time: 23:36
 */
public interface AliYunOssService {
    static final String AVATAR = "avatar/";

    boolean uploadToOss(String filePath, String ossFilePath);

    String getOssUrl(String bucket, String folder, String fileName);

    String getFolder(int type);

}

