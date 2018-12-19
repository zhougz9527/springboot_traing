package com.example.springboot_traing.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot_traing.global.Constants;
import com.example.springboot_traing.utils.HttpRequestUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Think
 * @Date: 2018/12/19
 * @Time: 23:29
 */
@Service
public class WeatherServiceImpl extends BaseServiceImpl implements WeatherService {


    @Override
    public Map<String, String> getIpCity(String ip) {
        ip = "127.0.0.1".equals(ip) ? "36.24.186.191" : ip;
        String url = Constants.IP_URL + ip;
        Map<String, String> headers = new HashMap<>();
        headers.put("Referer", "https://www.google.com.hk/");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        headers.put("X-DevTools-Emulate-Network-Conditions-Client-Id", "5029D206E9CA0EA382EE231E9A595079");
        headers.put("DNT", "1");
        String response = HttpRequestUtil.get(url,headers);
        JSONObject jsonObject = JSON.parseObject(response);
        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
        String city = jsonObject1.getString("city");
        String country = jsonObject1.getString("country");
        String region = jsonObject1.getString("region");
        String isp = jsonObject1.getString("isp");
        Map<String, String> map = new HashMap<>();
        map.put("country", country);
        map.put("region", region);
        map.put("city", city);
        map.put("isp", isp);
        return map;
    }
}
