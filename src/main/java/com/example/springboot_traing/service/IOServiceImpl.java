package com.example.springboot_traing.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: Think
 * @Date: 2018/12/14
 * @Time: 0:03
 */
@Service
public class IOServiceImpl extends BaseServiceImpl implements IOService {


    @Override
    public String download(MultipartFile file, String downloadPath) {
        String path = "";
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            byte[] bytes = file.getBytes();
            File downloadFile = new File(downloadPath);
            fos = new FileOutputStream(downloadFile);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("download file exception: {}", e.getMessage());
        } finally{
            try {
                if (null != bos) {
                    bos.close();
                }
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("close stream exception: {}", e.getMessage());
            }
        }
        return path;
    }

    @Override
    public String getFileNameSuffix(MultipartFile file) {
        String fileName = StringUtils.isEmpty(file.getOriginalFilename()) ? file.getName() : file.getOriginalFilename();
        String[] fileNameSplits = fileName.split("\\.");
        return fileNameSplits[fileNameSplits.length-1];
    }
}
