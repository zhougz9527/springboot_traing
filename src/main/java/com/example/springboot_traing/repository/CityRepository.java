package com.example.springboot_traing.repository;

import com.example.springboot_traing.entity.City;

import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/21
 * @Time: 17:55
 */
public interface CityRepository extends BaseRepository<City, Integer>{

    City save(City city);

    Optional<City> findByName(String name);

}
