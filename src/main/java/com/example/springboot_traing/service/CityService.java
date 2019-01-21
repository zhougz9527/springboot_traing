package com.example.springboot_traing.service;

import com.example.springboot_traing.entity.City;

import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/21
 * @Time: 19:38
 */
public interface CityService {

    City save (int pid, String name, String code);

    Optional<City> findByName(String name);

}
