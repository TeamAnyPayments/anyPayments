package com.artist.wea.api.service.Impl;

import com.artist.wea.api.service.UserService;
import com.artist.wea.config.security.JwtTokenProvider;
import com.artist.wea.db.dto.request.user.JoinPostReqDTO;
import com.artist.wea.db.dto.response.user.JoinPostResDTO;
import com.artist.wea.db.entity.User;
import com.artist.wea.db.entity.base.Role;
import com.artist.wea.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public JoinPostResDTO join(JoinPostReqDTO joinPostReqDto) {
        if (!joinPostReqDto.isCheckId()) {
            throw new IllegalArgumentException("아이디 중복 확인이 필요합니다.");
        }
        if (!joinPostReqDto.isCheckEmail()) {
            throw new IllegalArgumentException("이메일 중복 확인이 필요합니다.");
        }

        User user = User.builder()
                .id(joinPostReqDto.getId())
                .password(passwordEncoder.encode(joinPostReqDto.getPassword()))
                .name(joinPostReqDto.getName())
                .email(joinPostReqDto.getEmail())
                .terms(joinPostReqDto.isTerms())
                .activated(true)
                .role(Role.USER)
                .build();
        userRepository.save(user);

        JoinPostResDTO joinPostResDto = JoinPostResDTO.builder()
                .id(joinPostReqDto.getId())
                .name(joinPostReqDto.getName())
                .email(joinPostReqDto.getEmail())
                .role(Role.USER)
                .build();

        return joinPostResDto;
    }

    @Override
    public String login(String userId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String token = jwtTokenProvider.generateAccessToken(authentication);
        redisTemplate.opsForValue().set(userId, token, jwtTokenProvider.getExpiration(token));
        return token;
    }

    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (redisTemplate.opsForValue().get("JWT:" + authentication.getName()) != null) {
            redisTemplate.delete(authentication.getName());
        }
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public boolean duplicateId(String userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public void changePass(String userId, String password, String passCheck) {
        User user = userRepository.findById(userId).get();
        if (!password.equals(passCheck)) throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        user.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public boolean checkPass(String pass, String password) {
        if (pass.equals(passwordEncoder.encode(password))) return true;
        return false;
    }


}