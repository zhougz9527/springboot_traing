package com.example.springboot_traing.service;

import java.util.Map;

/**
 * @Author: Think
 * @Date: 2018/12/19
 * @Time: 23:29
 */
public interface WeatherService {

    Map<String, String> getIpCity(String ip);

}
