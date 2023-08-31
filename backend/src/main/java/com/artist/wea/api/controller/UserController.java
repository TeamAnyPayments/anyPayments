package com.artist.wea.api.controller;

import com.artist.wea.api.service.MailService;
import com.artist.wea.api.service.UserService;
import com.artist.wea.config.security.UserPrincipal;
import com.artist.wea.config.security.filter.JwtFilter;
import com.artist.wea.db.dto.request.user.JoinPostReqDTO;
import com.artist.wea.db.dto.request.user.LoginPostReqDTO;
import com.artist.wea.db.dto.util.ResponseDTO;
import com.artist.wea.db.entity.User;
import com.artist.wea.db.entity.UserImg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Tag(name = "사용자 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    /**
     * 회원 정보 조회 API
     */
    @Operation(summary = "회원 정보 조회 API")
    @GetMapping
    public ResponseEntity<ResponseDTO> getUserDetail(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "회원 정보 조회 완료", userService.getUser(userPrincipal.getUsername()).get()));
    }

    /**
     * 회원 가입 API
     */
    @Operation(summary = "회원 가입 API")
    @PostMapping
    public ResponseEntity<ResponseDTO> joinUser(@RequestBody JoinPostReqDTO joinPostReqDto) {
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "회원 가입 완료", userService.join(joinPostReqDto)));
    }

    /**
     * 로그인 API
     */
    @Operation(summary = "로그인 API")
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginPostReqDTO loginPostReqDto) {
        String jwt = userService.login(loginPostReqDto.getId(), loginPostReqDto.getPassword());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "로그인 완료", jwt));
    }

    /**
     * 로그아웃 API
     */
    @Operation(summary = "로그아웃 API")
    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO> logoutUser(@RequestBody Map<String, String> tokenMap) {
        userService.logout(tokenMap.get("token"));
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "로그아웃 완료"));
    }

    /**
     * 아이디 찾기 API
     */
    @Operation(summary = "아이디 찾기 API")
    @PostMapping("/id/find")
    public ResponseEntity<ResponseDTO> findId(@RequestParam("email") String email, @RequestParam("name") String name) throws Exception {
        mailService.findId(email, name);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "아이디 찾기 완료"));
    }

    /**
     * 아이디 중복 확인 API
     */
    @Operation(summary = "아이디 중복 확인 API")
    @PostMapping("/id/check")
    public ResponseEntity<ResponseDTO> duplicateId(@RequestParam("id") String userId) {
        if (userService.duplicateId(userId))
            return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, "이미 사용중인 아이디"));
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "아이디 사용 가능"));
    }

    /**
     * 비밀번호 찾기 API
     */
    @Operation(summary = "비밀번호 찾기 API")
    @PostMapping("/pass/find")
    public ResponseEntity<ResponseDTO> findPass(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("id") String userId) throws Exception {
        mailService.findPass(email, name, userId);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "입력한 회원 정보 일치"));
    }

    /**
     * 비밀번호 변경 API
     */
    @Operation(summary = "비밀번호 변경 API")
    @PatchMapping("/pass/change")
    public ResponseEntity<ResponseDTO> changePass(@RequestParam("id") String userId, @RequestParam("password") String password, @RequestParam("passCheck") String passCheck) throws Exception {
        userService.changePass(userId, password, passCheck);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "비밀번호 변경 완료"));
    }

    /**
     * 비밀번호 확인 API
     */
    @Operation(summary = "비밀번호 확인 API")
    @PostMapping("/pass/check")
    public ResponseEntity<ResponseDTO> checkPass(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("password") String password) throws Exception {
        userService.checkPass(userPrincipal.getPassword(), password);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "비밀번호 일치"));
    }

    /**
     * 이메일 인증 보내기 API
     */
    @Operation(summary = "이메일 인증 보내기 API")
    @PostMapping("/email/send")
    public ResponseEntity<ResponseDTO> sendEmail(@RequestParam("email") String email) throws Exception {
        mailService.sendSimpleMessage(email);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "이메일 인증번호 전송"));
    }

    /**
     * 이메일 인증 확인 API
     */
    @Operation(summary = "이메일 인증 확인 API")
    @PostMapping("/email/check")
    public ResponseEntity<ResponseDTO> checkEmail(@RequestParam("email") String email, @RequestParam("code") String code) throws Exception {
        if (mailService.checkCode(email, code))
            return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "이메일 인증 완료"));
        return ResponseEntity.badRequest().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, "이메일 인증 실패"));
    }

    /**
     * 프로필 이미지 조회 API
     */
    @Operation(summary = "프로필 이미지 조회 API")
    @GetMapping("/image")
    public ResponseEntity<?> getImage(@AuthenticationPrincipal UserPrincipal userPrincipal) throws IOException {
        UserImg userImg = userService.getImage(userPrincipal.getUsername());
        if(userImg == null) return ResponseEntity.ok(ResponseDTO.of(HttpStatus.OK, "등록된 프로필 이미지가 없습니다."));

        Resource resource = new FileSystemResource(userImg.getFileUrl());
        HttpHeaders header = new HttpHeaders();
        Path filePath = Paths.get(userImg.getFileUrl());
        header.add("Content-Type", Files.probeContentType(filePath));

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

    /**
     * 프로필 이미지 업로드 API
     */
    @Operation(summary = "프로필 이미지 업로드 API")
    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                         @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (userService.validImgFile(multipartFile)) {
            User user = userService.getUser(userPrincipal.getUsername()).get();
            UserImg userImg = userService.uploadImage(multipartFile);
            user.setUserImg(userImg);
            userService.updateUser(user);
            return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "프로필 이미지 업로드 성공"));
        }
        return ResponseEntity.badRequest().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, "올바른 이미지 파일이 아닙니다"));
    }

    /**
     * 프로필 이미지 수정 API
     */
    @Operation(summary = "프로필 이미지 수정 API")
    @PutMapping("/image")
    public ResponseEntity<?> updateImage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                         @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (userService.validImgFile(multipartFile)) {
            User user = userService.getUser(userPrincipal.getUsername()).get();
            userService.updateImage(multipartFile, user);
            userService.updateUser(user);
            return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "프로필 이미지 업데이트 성공"));
        }
        return ResponseEntity.badRequest().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, "올바른 이미지 파일이 아닙니다"));
    }

    /**
     * 프로필 이미지 삭제 API
     */
    @Operation(summary = "프로필 이미지 삭제 API")
    @DeleteMapping("/image")
    public ResponseEntity<?> deleteImage(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        userService.deleteImage(userPrincipal.getUsername());
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK,  "프로필 이미지 삭제 성공"));
    }

}