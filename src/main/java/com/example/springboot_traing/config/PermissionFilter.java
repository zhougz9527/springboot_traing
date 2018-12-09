package com.example.springboot_traing.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 22:37
 */
@WebFilter(urlPatterns = {"/user/*"}, filterName = "PermissionFilter")
public class PermissionFilter implements Filter {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        httpServletResponse.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers","X-SpringBoot-Token, *");

        String token = httpServletRequest.getHeader("X-SpringBoot-Token");
        String method = httpServletRequest.getMethod();

        if ("OPTIONS".equals(method)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            // TODO ...
        }

    }

    @Override
    public void destroy() {

    }
}
