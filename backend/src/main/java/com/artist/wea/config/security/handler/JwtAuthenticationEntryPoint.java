package com.artist.wea.config.security.handler;

import com.artist.wea.common.exception.errorcode.CommonErrorCode;
import com.artist.wea.common.exception.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 인증 실패시 결과를 처리해주는 클래스
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("[commence] 인증 실패");

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(CommonErrorCode.UNAUTHORIZED.getStatus())
                .code(CommonErrorCode.UNAUTHORIZED.getCode())
                .message(CommonErrorCode.UNAUTHORIZED.getMessage())
                .build();

        response.setStatus(CommonErrorCode.UNAUTHORIZED.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
