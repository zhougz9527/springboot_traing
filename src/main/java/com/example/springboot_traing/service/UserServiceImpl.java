package com.example.springboot_traing.service;

import com.example.springboot_traing.entity.User;
import com.example.springboot_traing.repository.UserRepository;
import com.example.springboot_traing.utils.CommonUtil;
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

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /*
     * 校验账号、密码
     */
    @Override
    public User checkUserByUsernameAndPassword(String username, String password) {
        return findByUsername(username).map(user -> {
            if (BCrypt.checkpw(password, user.getPassword())) {
               String token = CommonUtil.md5(CommonUtil.random(11, CommonUtil.STRING) + System.currentTimeMillis(), true);
               user.setLastLogin(new Timestamp(System.currentTimeMillis()));
               user.setToken(token);
               return user;
            } else {
                return null;
            }
        }).orElse(null);
    }

    /*
     * 重置密码
     */
    @Override
    public Optional<User> resetPassword(String username, String password) {
        return findByUsername(username).map(user -> {
            String newPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(newPassword);
            save(user);
            return Optional.of(user);
        }).orElse(Optional.empty());
    }
}
