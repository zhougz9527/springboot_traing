package com.example.springboot_traing.service;

import com.example.springboot_traing.entity.City;
import com.example.springboot_traing.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/21
 * @Time: 19:41
 */
@Service
public class CityServiceImpl extends BaseServiceImpl implements CityService {

    @Autowired
    CityRepository cityRepository;

    @Override
    public City save(int pid, String name, String code) {
        City city = new City();
        city.setPid(pid);
        city.setName(name);
        city.setCode(code);
        return cityRepository.save(city);
    }

    @Override
    public Optional<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

}
