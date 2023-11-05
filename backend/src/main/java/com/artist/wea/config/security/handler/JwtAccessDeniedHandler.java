package com.artist.wea.config.security.handler;

import com.artist.wea.common.exception.errorcode.CommonErrorCode;
import com.artist.wea.common.exception.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 권한이 없는 예외가 발생했을 경우 핸들링하는 클래스
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(JwtAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("[handle] 접근 권한이 없습니다.");


        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(CommonErrorCode.FORBIDDEN_ACCESS.getStatus())
                .code(CommonErrorCode.FORBIDDEN_ACCESS.getCode())
                .message(CommonErrorCode.FORBIDDEN_ACCESS.getMessage())
                .build();

        response.setStatus(CommonErrorCode.FORBIDDEN_ACCESS.getStatus());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
