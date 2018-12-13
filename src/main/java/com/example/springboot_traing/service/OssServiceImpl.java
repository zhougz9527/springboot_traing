package com.example.springboot_traing.service;


import com.example.springboot_traing.global.Constants;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

/**
 * @Author: Think
 * @Date: 2018/12/13
 * @Time: 23:37
 */
@Service
public class OssServiceImpl extends BaseServiceImpl implements OssService {

    @Override
    public boolean uploadToQiniu(String filePath, String fileName) {
        boolean success = false;
        Configuration configuration = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(configuration);
        Auth auth = Auth.create(Constants.QINIU_ACCESSKEY, Constants.QINIU_SECRETKEY);
        String uploadToken = auth.uploadToken(Constants.QINIU_BUCKET);
        try {
            Response response = uploadManager.put(filePath, fileName, uploadToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            logger.info("putRet key: {}", putRet.key);
            logger.info("putRet hash: {}", putRet.hash);
            success = true;
        } catch (QiniuException e) {
            Response response = e.response;
            logger.error(response.toString());
            try {
                logger.error(response.bodyString());
            } catch (QiniuException e1) {
                e1.printStackTrace();
            }
        }
        return success;
    }

}
