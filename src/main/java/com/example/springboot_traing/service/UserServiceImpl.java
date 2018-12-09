package com.example.springboot_traing.service;

import com.example.springboot_traing.entity.User;
import com.example.springboot_traing.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 23:46
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> save(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public boolean createUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        } else {
            String cryptPwd = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User();
            user.setAvatar("");
            user.setUsername(username);
            user.setPassword(cryptPwd);
            user.setLastLogin(new Timestamp(System.currentTimeMillis()));
            user.setGtime(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            return true;
        }
    }
}
