package com.example.springboot_traing.config;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.example.springboot_traing.entity.User;
import com.example.springboot_traing.result.Result;
import com.example.springboot_traing.result.ResultUtil;
import com.example.springboot_traing.service.RedisService;
import com.example.springboot_traing.service.UserService;
import com.example.springboot_traing.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @Author: Think
 * @Date: 2018/12/9
 * @Time: 22:37
 */
@WebFilter(urlPatterns = {"/user/*"}, filterName = "permissionFilter")
public class PermissionFilter implements Filter {

    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;

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

        boolean success = false;
        Result result = null;
        if (method.equals("OPTIONS")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            if (!StringUtils.isEmpty(token)) {
                Claims claims = JWTUtil.parseJWT(token);
                if (JWTUtil.verifyJWT(token) && null != claims) {
                    String username = (String) claims.get("username");
                    User user = userService.findByUsername(username).orElse(null);
                    if (null != user) {
                        httpServletRequest.setAttribute("user", user);
                        success = true;
                    }
                }
            } else {
                result = ResultUtil.error(210);
            }

            if (success) {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                PrintWriter writer = null;
                OutputStreamWriter osw = null;
                try {
                    osw = new OutputStreamWriter(httpServletResponse.getOutputStream(), "UTF-8");
                    writer = new PrintWriter(osw, true);
                    String jsonStr = JSON.toJSONString(result);
                    writer.write(jsonStr);
                    writer.flush();
                    writer.close();
                    osw.close();
                    logger.info(result.toString());
                } catch (Exception e) {
                    logger.error(" permission filter exception: {}", e.getMessage());
                } finally {
                    if (null != writer) {
                        writer.close();
                    }
                    if (null != osw) {
                        osw.close();
                    }
                }
            }
        }

    }

    @Override
    public void destroy() {

    }
}
