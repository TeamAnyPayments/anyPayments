package com.artist.wea.config.security;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
    private final UserDetailsService userDetailsService;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; //access 30분


    @Value("${spring.jwt.secret}")
    private String secretKey = "secretKey";

    /**
     * secretKey 인코딩 수행
     */
    @PostConstruct
    protected void init() {
        LOGGER.info("[init] secretKey 초기화 시작");
        LOGGER.info("{} before encoding", secretKey);

        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));

        LOGGER.info("[init] secretKey 초기화 완료");
        LOGGER.info("{} after encoding", secretKey);
    }


    public String createToken(String userId) {
        LOGGER.info("[createToken] 토큰 생성 시작");
        Claims claims = Jwts.claims().setSubject(userId);

        Date now = new Date();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME)) // set Expire Time 해당 옵션 안넣으면 expire안함
                .signWith(SignatureAlgorithm.HS256, secretKey) // 사용할 암호화 알고리즘과 , signature 에 들어갈 secret값 세팅
                .compact();

        LOGGER.info("[createToken] 토큰 생성 완료");
        return token;
    }

    /**
     * JWT 토큰을 받아 인증 정보 조회
     *
     * @param token
     * @return Authentication
     */
    public Authentication getAuthentication(String token) {
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        LOGGER.info("[getAuthentication] 토큰 인증 정보 조회 완료. id : {}", userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
    }

    /**
     * JWT 토큰에서 회원 구별 정보 추출
     *
     * @param token
     * @return email;
     */
    private String getUsername(String token) {
        LOGGER.info("[getUsername] 토큰 기반 회원 구별 정보 추출 시작");
        String id = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        LOGGER.info("[getUsername] 토큰 기반 회원 구별 정보 추출 완료. id : {}", id);
        return id;
    }

    /**
     * HTTP Request Header에 설정된 토큰 값을 가져옴
     *
     * @param request
     * @return token
     */
    public String resolveToken(HttpServletRequest request) {
        LOGGER.info("[resolveToken] HTTP 헤더에서 Token 값 추출");
        return request.getHeader("X-AUTH-TOKEN");
    }

    /**
     * JWT 토큰 유효성, 만료일 체크
     *
     * @param token
     * @return boolean
     */
    public boolean validateToken(String token) {
        LOGGER.info("[validateToken] 토큰 유효성 체크 시작");
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            LOGGER.info("[validateToken] 토큰 유효성 체크 완료");
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            LOGGER.info("[validateToken] 잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            LOGGER.info("[validateToken] 만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            LOGGER.info("[validateToken] 지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            LOGGER.info("[validateToken] JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

}
