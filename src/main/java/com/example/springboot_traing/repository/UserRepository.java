package com.example.springboot_traing.repository;

import com.example.springboot_traing.entity.User;

import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 23:44
 */
public interface UserRepository extends BaseRepository<User, Integer> {

    User save(User user);

    Optional<User> findByUsername(String username);

}
