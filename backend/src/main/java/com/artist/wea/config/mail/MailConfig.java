package com.artist.wea.config.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${mail.smtp.host}")
    private String host;
    @Value("${mail.smtp.username}")
    private String username;
    @Value("${mail.smtp.password}")
    private String password;
    @Value("${mail.smtp.port}")
    private int port;
    @Value("${mail.transport.protocol}")
    private String protocol;
    @Value("${mail.smtp.auth}")
    private String auth;
    @Value("${mail.smtp.starttls.enable}")
    private String starttls;
    @Value("${mail.smtp.ssl.trust}")
    private String ssl_trust;
    @Value("${mail.smtp.ssl.enable}")
    private String ssl_enable;


    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);   //smtp 서버주소
        javaMailSender.setUsername(username);    //아이디
        javaMailSender.setPassword(password); //비밀번호
        javaMailSender.setPort(port);    //메일 인증서버 포트
        javaMailSender.setJavaMailProperties(getMailProperties());  //메일 인증 서버 정보 가져오기

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", protocol);  //프로토콜 설정
        properties.setProperty("mail.smtp.auth", auth);    //smtp  인증
        properties.setProperty("mail.smtp.starttls.enable", starttls);    //smtp starttles 사용
        properties.setProperty("mail.smtp.ssl.trust", ssl_trust);    //ssl 인증 서버
        properties.setProperty("mail.smtp.ssl.enable", ssl_enable);    //ssl 사용

        return properties;
    }
}