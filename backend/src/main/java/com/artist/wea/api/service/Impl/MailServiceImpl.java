package com.artist.wea.api.service.Impl;

import com.artist.wea.api.service.MailService;
import com.artist.wea.common.util.RedisUtil;
import com.artist.wea.db.entity.User;
import com.artist.wea.db.repository.UserRepository;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;

    @Override
    public MimeMessage createMessage(String title, String to, String ePw) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to); //보내는 대상
        message.setSubject(title + " 인증 메일"); // 제목

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> WE:A 입니다</h1>";
        msgg += "<br>";
        msgg += "<p>아래 코드를 " + title + " 창으로 돌아가 입력해주세요<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>" + title + " 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw + "</strong><div><br/> "; // 메일에 인증번호 넣기
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
        message.setFrom(new InternetAddress("wea-support@naver.com", "wea-support"));// 보내는 사람

        return message;

    }

    @Override
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }

        return key.toString();
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        String ePw = createKey(); // 랜덤 인증번호 생성
        MimeMessage message = createMessage("회원가입", to, ePw); // 메일 발송
        try {
            mailSender.send(message);
            redisUtil.setDataExpire(to, ePw, 1000L * 60L * 5L);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }

        return ePw; // 메일로 보냈던 인증 코드를 서버로 반환
    }

    @Override
    public boolean checkCode(String email, String code) {
        String getValue = redisUtil.getData(email);
        if (getValue == null || !getValue.equals(code)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void findId(String email, String name) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByEmailAndName(email, name).orElseThrow(() -> new IllegalArgumentException("일치하는 회원 정보가 없습니다."));

        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, email); //보내는 대상
        message.setSubject("아이디 찾기"); // 제목

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> WE:A 입니다</h1>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원님이 가입한 아이디입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "ID : <strong>";
        msgg += user.getId() + "</strong><div><br/> ";
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
        message.setFrom(new InternetAddress("wea-support@naver.com", "wea-support"));// 보내는 사람

        try {// 예외처리
            mailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String findPass(String email, String name, String userId) throws MessagingException, UnsupportedEncodingException {
        User user = userRepository.findByIdAndEmailAndName(userId, email, name).orElseThrow(() -> new IllegalArgumentException("일치하는 회원 정보가 없습니다."));

        String ePw = createKey(); // 랜덤 인증번호 생성
        MimeMessage message = createMessage("비밀번호 찾기", email, ePw); // 메일 발송
        try {// 예외처리
            mailSender.send(message);
            redisUtil.setDataExpire(email, ePw, 1000L * 60L * 5L);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }

        return ePw; // 메일로 보냈던 인증 코드를 서버로 반환
    }

    @Override
    public void sendMail(String email, String content) throws UnsupportedEncodingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("wea-support@naver.com"); // 관리자 메일 주소
        message.setSubject("사용자 문의");
        message.setText("사용자 이메일: " + email + "\n\n" + "문의 내용:\n" + content);

        try {// 예외처리
            message.setFrom("wea-support@naver.com");// 보내는 사람
            mailSender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException();
        }
    }

}