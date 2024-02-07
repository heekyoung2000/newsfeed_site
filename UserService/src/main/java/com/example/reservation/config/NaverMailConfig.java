package com.example.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class NaverMailConfig {
    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.naver.com");
        mailSender.setPort(465);
        mailSender.setUsername("아이디");
        mailSender.setPassword("비밀번호");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.transport.protocol","smtp");
        javaMailProperties.put("mail.smtp.auth","true");
        javaMailProperties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //SSL 소켓 팩토리 클래스 사용
        javaMailProperties.put("mail.smtp.starttls.enalbe","true");
        javaMailProperties.put("mail.smtp.ssl.enable", "true");
        javaMailProperties.put("mail.debug","true");
        javaMailProperties.put("mail.smtp.ssl.trust","smtp.naver.com");
        javaMailProperties.put("mail.smtp.ssl.protocols","TLSv1.2");

        mailSender.setJavaMailProperties(javaMailProperties);

        System.out.println(mailSender);
        System.out.println(javaMailProperties);
        return mailSender;
    }
}
