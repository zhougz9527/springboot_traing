package com.example.springboot_traing.service;

import com.example.springboot_traing.entity.User;

import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 23:46
 */
public interface UserService {

    Optional<User> save(User user);

    boolean createUser(String username, String password);

    Optional<User> findByUsername(String username);

    User checkUserByUsernameAndPassword(String username, String password);

    Optional<User> resetPassword(String username, String password);

    String resetUserToken(int uid);

    boolean deleteUserToken(int uid);

}
