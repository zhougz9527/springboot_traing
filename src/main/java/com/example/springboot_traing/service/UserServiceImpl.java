package com.example.springboot_traing.service;

import com.example.springboot_traing.entity.User;
import com.example.springboot_traing.repository.UserRepository;
import com.example.springboot_traing.utils.CommonUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 23:46
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisService redisService;

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
               user.setLastLogin(new Timestamp(System.currentTimeMillis()));
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

    /*
     * 重置用户登录的token
     */
    @Override
    public String resetUserToken(int uid) {
        String token = UUID.randomUUID().toString();
        Object object = redisService.get(redisService.USER + uid);
        if (null != object) {
            redisService.delete(redisService.TOKEN + object.toString());
        }
        return userRepository.findById(uid).map(user -> {
            redisService.set(redisService.USER + uid, token, redisService.TOKEN_EXPIRES);
            redisService.set(redisService.TOKEN + token, user, redisService.TOKEN_EXPIRES);
            return token;
        }).orElse("");
    }

    /*
     * 删除用户的token
     */
    @Override
    public boolean deleteUserToken(int uid) {
        boolean success = true;
        if (redisService.exists(redisService.USER + uid)) {
            String token = redisService.get(redisService.USER + uid).toString();
            if (!redisService.delete(redisService.USER + uid) || !redisService.delete(redisService.TOKEN + token)) {
                success = false;
            }
        }
        return success;
    }
}
