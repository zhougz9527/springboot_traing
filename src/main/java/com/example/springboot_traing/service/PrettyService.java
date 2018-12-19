package com.example.springboot_traing.service;

import com.example.springboot_traing.entity.Pretty;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/19
 * @Time: 18:18
 */
public interface PrettyService {

    Optional<Pretty> findFirstByValidOrderByIdAsc(int valid);

    Pretty save(String url);

    Pretty save(Pretty pretty);

    List<String> crawlPretty(int page);

    Optional<Pretty> findByImage(String image);

}
