package com.example.springboot_traing.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springboot_traing.entity.User;
import com.example.springboot_traing.global.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 该项目前不适用jwt
 * @Author: Think
 * @Date: 2019/1/8
 * @Time: 17:47
 */
public class JWTUtil {

    private static final Logger logger = LogManager.getLogger();

    /**
     * 生成jwt签名
     * @param user
     * @param ttlMillis 毫秒单位
     * @return
     */
    public static String generateJWT(long ttlMillis, User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long currentMillis = System.currentTimeMillis();
        Date now = new Date(currentMillis);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());

        String key = Constants.JWT_SECRET + user.getPassword();
        String subject = user.getUsername();

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);

        if (ttlMillis > 0) {
            long expireMillis = currentMillis + ttlMillis;
            Date expiresDate = new Date(expireMillis);
            builder.setExpiration(expiresDate);
        }
        return builder.compact();
    }

    /**
     * 解析jwt签名
     * @param token
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String token) {
        try{
            return Jwts.parser()
                    .setSigningKey(Constants.JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.error("parseJWT failed: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 验证JWT签名
     * @param token
     * @return
     */
    public static boolean verifyJWT(String token) {
        boolean success = false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(Constants.JWT_SECRET);
            Claims claims = parseJWT(token);
            if (null != claims) {
                String username = (String) claims.get("username");
                JWTVerifier verifier = JWT.require(algorithm)
                        .withClaim("username", username)
                        .build();
                verifier.verify(token);
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("jwt token failed, error message: {}", e.getMessage());
        }
        return success;
    }




}
