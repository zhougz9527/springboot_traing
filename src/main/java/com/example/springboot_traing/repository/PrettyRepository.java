package com.example.springboot_traing.repository;

import com.example.springboot_traing.entity.Pretty;

import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/19
 * @Time: 18:13
 */
public interface PrettyRepository extends BaseRepository<Pretty, Integer> {

    Optional<Pretty> findFirstByValidOrderByIdAsc(byte valid);

    Pretty save(String url);

    Pretty save(Pretty pretty);

    Optional<Pretty> findByImage(String image);
}
