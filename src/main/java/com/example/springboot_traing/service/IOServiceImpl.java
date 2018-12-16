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
    public String download(MultipartFile file, String downloadFolder, String fileName) {
        String path = "";
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        String downloadFilePath = downloadFolder + File.separator + fileName;
        try {
            byte[] bytes = file.getBytes();
            File folder = new File(downloadFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            fos = new FileOutputStream(downloadFilePath);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
            bos.flush();
            path = downloadFilePath;
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

    @Override
    public String getFileName(MultipartFile file) {
        return StringUtils.isEmpty(file.getOriginalFilename()) ? file.getName() : file.getOriginalFilename();
    }

    @Override
    public boolean delete(String filePath) {
        boolean success = false;
        if (!StringUtils.isEmpty(filePath)) {
            File file = new File(filePath);
            success = file.delete();
        }
        return success;
    }
}
