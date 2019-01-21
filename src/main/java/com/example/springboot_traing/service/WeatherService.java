package com.example.springboot_traing.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @Author: Think
 * @Date: 2018/12/19
 * @Time: 23:29
 */
public interface WeatherService {

    Map<String, String> getIpCity(String ip);

    JSONObject getWeatherByName(String name);

}
