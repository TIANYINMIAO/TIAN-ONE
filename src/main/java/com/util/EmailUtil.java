package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;
    // 注入 spring.mail.username 的值
    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendVerificationEmail(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);;
        message.setTo(toEmail);
        message.setSubject("注册验证码");
        message.setText("您的注册验证码是：" + code);
        mailSender.send(message);
    }
}
