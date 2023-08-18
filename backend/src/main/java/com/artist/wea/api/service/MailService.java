package com.artist.wea.api.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

public interface MailService {

    MimeMessage createMessage(String title, String to, String ePw) throws MessagingException, UnsupportedEncodingException;

    String createKey();

    String sendSimpleMessage(String to) throws Exception;

    boolean checkCode(String email, String code);

    void findId(String email, String name) throws MessagingException, UnsupportedEncodingException;

    String findPass(String email, String name, String userId) throws MessagingException, UnsupportedEncodingException;
}
