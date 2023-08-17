package com.artist.wea.config.security.handler;

import com.artist.wea.db.dto.util.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");

        response.setStatus(403);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(responseDTO));
    }

}
