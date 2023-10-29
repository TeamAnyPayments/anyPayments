package com.artist.wea.api.service.Impl;

import com.artist.wea.api.service.UserService;
import com.artist.wea.common.exception.CommonApiException;
import com.artist.wea.common.exception.errorcode.CommonErrorCode;
import com.artist.wea.common.util.FileValidator;
import com.artist.wea.config.security.JwtTokenProvider;
import com.artist.wea.db.dto.request.user.JoinPostReqDTO;
import com.artist.wea.db.dto.response.user.JoinPostResDTO;
import com.artist.wea.db.entity.User;
import com.artist.wea.db.entity.UserImg;
import com.artist.wea.db.entity.base.Role;
import com.artist.wea.db.repository.UserImgRepository;
import com.artist.wea.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserImgRepository userImgRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisTemplate<String, String> redisTemplate;
    @Value("${file.path}")
    private String path;

    /**
     * 회원가입
     */
    @Transactional
    public JoinPostResDTO join(JoinPostReqDTO joinPostReqDto) {
        if (!joinPostReqDto.isCheckId()) {
            throw new CommonApiException(CommonErrorCode.USER_NOT_CHECK_ID);
        }
        if (!joinPostReqDto.isCheckEmail()) {
            throw new CommonApiException(CommonErrorCode.USER_NOT_CHECK_EMAIL);
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

    /**
     * 로그인
     */
    @Override
    public String login(String userId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String token = jwtTokenProvider.generateAccessToken(authentication);
        return token;
    }

    /**
     * 로그아웃
     */
    public void logout(String token) {
        redisTemplate.opsForValue().set(token, "logout", jwtTokenProvider.getExpiration(token));
    }

    /**
     * 사용자 정보 조회
     */
    @Transactional(readOnly = true)
    public Optional<User> getUser(String userId) {
        return userRepository.findById(userId);
    }

    /**
     * 아이디 중복 확인
     */
    @Override
    public boolean duplicateId(String userId) {
        return userRepository.existsById(userId);
    }

    /**
     * 비밀번호 변경
     */
    @Override
    public void changePass(String userId, String password, String passCheck) {
        User user = userRepository.findById(userId).get();
        if (!password.equals(passCheck)) throw new CommonApiException(CommonErrorCode.USER_INVALID_PASSWORD);
        user.setPassword(passwordEncoder.encode(password));
    }

    /**
     * 비밀번호 확인
     */
    @Override
    public boolean checkPass(String pass, String password) {
        if (pass.equals(passwordEncoder.encode(password))) return true;
        return false;
    }

    /**
     * 사용자 정보 수정
     */
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 이미지 파일 유효성 검증
     */
    @Override
    public boolean validImgFile(MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            if (!multipartFile.isEmpty()) {
                boolean isValid = FileValidator.validImgFile(inputStream);
                if (!isValid) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 프로필 이미지 조회
     */
    @Override
    public UserImg getImage(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonApiException(CommonErrorCode.USER_NOT_FOUND));
        UserImg userImg = (user.getUserImg() != null) ? userImgRepository.findById(user.getUserImg().getId()).get() : null;
        return userImg;
    }

    /**
     * 프로필 이미지 업로드
     */
    public UserImg uploadImage(MultipartFile multipartFile) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileUrl = path + uuid.toString() + "_" + multipartFile.getOriginalFilename();
        UserImg userImg = userImgRepository.save(
                UserImg.builder()
                        .name(multipartFile.getOriginalFilename())
                        .type(multipartFile.getContentType())
                        .fileUrl(fileUrl)
                        .build()
        );
        multipartFile.transferTo(new File(fileUrl));

        return userImg;
    }

    /**
     * 프로필 이미지 수정
     */
    public UserImg updateImage(MultipartFile multipartFile, User user) throws IOException {
        UUID uuid = UUID.randomUUID();
        String filePath = path + uuid.toString() + "_" + multipartFile.getOriginalFilename();

        UserImg userImg = userImgRepository.findById(user.getUserImg().getId()).get();

        File file = new File(userImg.getFileUrl());
        file.delete();

        userImg.updateUserImg(multipartFile, filePath);
        userImgRepository.save(userImg);
        multipartFile.transferTo(new File(filePath));

        return userImg;
    }

    /**
     * 프로필 이미지 삭제
     */
    public void deleteImage(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CommonApiException(CommonErrorCode.USER_NOT_FOUND));
        UserImg userImg = userImgRepository.findById(user.getUserImg().getId()).get();

        user.setUserImg(null);
        File file = new File(userImg.getFileUrl());
        file.delete();

        userImgRepository.deleteById(userImg.getId());
    }

}