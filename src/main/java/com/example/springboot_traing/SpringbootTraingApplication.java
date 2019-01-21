package com.example.springboot_traing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableScheduling
@EnableAsync
@ServletComponentScan
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
@SpringBootApplication
public class SpringbootTraingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTraingApplication.class, args);
    }
}