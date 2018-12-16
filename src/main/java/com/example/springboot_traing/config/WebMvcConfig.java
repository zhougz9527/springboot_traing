package com.example.springboot_traing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 22:20
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "HEAD", "OPTIONS", "POST", "PUT", "DELETE")
                .allowCredentials(false).maxAge(3600)
                .exposedHeaders("X-SpringBoot-Token");
    }
}
