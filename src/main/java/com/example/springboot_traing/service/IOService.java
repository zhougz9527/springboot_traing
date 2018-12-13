package com.example.springboot_traing.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Think
 * @Date: 2018/12/14
 * @Time: 0:02
 */
public interface IOService {

    String download(MultipartFile file, String downloadPath);

    String getFileNameSuffix(MultipartFile file);

}
