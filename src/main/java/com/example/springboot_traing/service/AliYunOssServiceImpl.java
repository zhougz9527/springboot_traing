package com.example.springboot_traing.service;


import com.aliyun.oss.OSSClient;
import com.example.springboot_traing.global.Constants;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Author: Think
 * @Date: 2018/12/13
 * @Time: 23:37
 */
@Service
public class AliYunOssServiceImpl extends BaseServiceImpl implements AliYunOssService {

    @Override
    public boolean uploadToOss(String filePath, String ossFilePath) {
        boolean success = false;
        try {
            OSSClient ossClient = new OSSClient(Constants.ALIYUN_ENDPOINT, Constants.ALIYUN_ACCESS_KEY_ID, Constants.ALIYUN_ACCESS_KEY_SECRET);
            ossClient.putObject(Constants.ALIYUN_BUCKET, ossFilePath, new File(filePath));
            ossClient.shutdown();
            success = true;
        } catch (Exception e) {
            logger.error("upload file to aliyun oss exception: {}", e.getMessage());
        }
        return success;
    }

    @Override
    public String getOssUrl(String bucket, String folder, String fileName) {
        return "https://" + bucket + ".oss-cn-hongkong.aliyuncs.com/" + folder + fileName;
    }


    @Override
    public String getFolder(int type) {
        String folderName = "";
        switch (type) {
            case 1:
                folderName = AVATAR;
        }
        return folderName;
    }

}
