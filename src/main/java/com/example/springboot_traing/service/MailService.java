package com.example.springboot_traing.service;

/**
 * @Author: Think
 * @Date: 2018/12/10
 * @Time: 21:14
 */
public interface MailService {

    void sendMailByAsync(String from, String[] receiver, String subject, String content, boolean isHtml);

    boolean sendMail(String receiver);
}
