package com.example.springboot_traing.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot_traing.entity.Pretty;
import com.example.springboot_traing.global.Constants;
import com.example.springboot_traing.repository.PrettyRepository;
import com.example.springboot_traing.utils.HttpRequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: Think
 * @Date: 2018/12/19
 * @Time: 18:19
 */
@Service
public class PrettyServiceImpl extends BaseServiceImpl implements PrettyService {

    @Autowired
    PrettyRepository prettyRepository;

    @Override
    public Optional<Pretty> findFirstByValidOrderByIdAsc(int valid) {
        return prettyRepository.findFirstByValidOrderByIdAsc((byte) valid);
    }

    @Override
    public Pretty save(String url) {
        Pretty pretty = new Pretty();
        pretty.setImage(url);
        pretty.setValid((byte) 1);
        return save(pretty);
    }

    @Override
    public Pretty save(Pretty pretty) {
        return prettyRepository.save(pretty);
    }

    @Override
    public List<String> crawlPretty(int page) {
        int size = 10;
        String url = Constants.PRETTY_URL + size + "/" + page;
        logger.info("request url: {}", url);
        String response = HttpRequestUtil.get(url,null);
        List<String> urls = new ArrayList<>();
        if (!StringUtils.isEmpty(response)) {
            JSONObject jsonObject = JSON.parseObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            if (null != jsonArray && jsonArray.size() != 0) {
                jsonArray.forEach(item -> {
                    JSONObject jsonObject1 = (JSONObject) item;
                    String imageUrl = jsonObject1.getString("url");
                    if (!StringUtils.isEmpty(imageUrl)) {
                        if (!findByImage(imageUrl).isPresent()) {
                            urls.add(imageUrl);
                            save(imageUrl);
                        }
                    }
                });
            }
        }
        return urls;
    }

    @Override
    public Optional<Pretty> findByImage(String image) {
        return prettyRepository.findByImage(image);
    }
}
