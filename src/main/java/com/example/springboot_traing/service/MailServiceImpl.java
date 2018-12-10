package com.example.springboot_traing.service;

import com.example.springboot_traing.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * @Author: Think
 * @Date: 2018/12/10
 * @Time: 21:14
 */
@Service
public class MailServiceImpl extends BaseServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RedisService redisService;

    @Value("${spring.mail.username}")
    String from;


    @Async
    @Override
    public void sendMailByAsync(String from, String[] receiver, String subject, String content, boolean isHtml) {
        try {
            logger.info(receiver.toString() + "send mail start ...");
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, isHtml);
            javaMailSender.send(message);
            logger.info(receiver.toString() + "send mail success ...");
        } catch (Exception e) {
            logger.error(receiver.toString() + "send mail Failure ... message: {}", e.getMessage());
        }

    }

    @Override
    public boolean sendMail(String receiver) {
        if (redisService.exists(RedisService.MAIL + receiver)) {
            return false;
        } else {
            String code = CommonUtil.random(6, CommonUtil.INTEGER);
            redisService.set(RedisService.MAIL + receiver, false, RedisService.MAIL_EXPIRES);
            redisService.set(RedisService.CODE + receiver, code, RedisService.CODE_EXPIRES);
            String[] to = {receiver};
            String subject = "SpringBoot Traing 验证码";
            String title = "<div style='margin-bottom:20px;'>你好,<br></div>";
            String text = "<div style='margin-bottom:20px;'>你注册的账号"+ receiver + "的验证码 :" +
                    "<br> <span style='font-size:18px'>" + code +"</span>" +
                    "<br> 五分钟内有效</div>";
            String signature = "<div style='margin-top:100px;'> Support by SpringBoot Traing</div>";
            String content = title + text + signature;
            sendMailByAsync(from, to, subject, content, true);
            return true;
        }
    }


}
