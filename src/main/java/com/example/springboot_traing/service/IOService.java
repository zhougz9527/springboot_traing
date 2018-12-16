package com.example.springboot_traing.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Think
 * @Date: 2018/12/14
 * @Time: 0:02
 */
public interface IOService {

    String download(MultipartFile file, String downloadFilePath, String fileName);

    String getFileNameSuffix(MultipartFile file);

    String getFileName(MultipartFile file);

    boolean delete(String filePath);

}
