package com.artist.wea.api.controller;

import com.artist.wea.api.service.MailService;
import com.artist.wea.api.service.UserService;
import com.artist.wea.config.security.UserPrincipal;
import com.artist.wea.config.security.filter.JwtFilter;
import com.artist.wea.db.dto.request.user.JoinPostReqDTO;
import com.artist.wea.db.dto.request.user.LoginPostReqDTO;
import com.artist.wea.db.dto.util.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getUserDetail(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "회원 정보 조회 완료", userService.getUser(userPrincipal.getUsername()).get()));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> joinUser(@RequestBody JoinPostReqDTO joinPostReqDto) {
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "회원 가입 완료", userService.join(joinPostReqDto)));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginPostReqDTO loginPostReqDto) {
        String jwt = userService.login(loginPostReqDto.getId(), loginPostReqDto.getPassword());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "로그인 완료", jwt));
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO> logoutUser(HttpServletRequest servletRequest) {
        userService.logout();
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "로그아웃 완료"));
    }

    // 아이디 찾기
    @PostMapping("/id/find")
    public ResponseEntity<ResponseDTO> findId(@RequestParam("email") String email, @RequestParam("name") String name) throws Exception {
        mailService.findId(email, name);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "아이디 찾기 완료"));
    }

    // 아이디 중복 확인
    @PostMapping("/id/check")
    public ResponseEntity<ResponseDTO> duplicateId(@RequestParam("id") String userId) {
        if (userService.duplicateId(userId))
            return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, "이미 사용중인 아이디"));
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "아이디 사용 가능"));
    }

    // 비밀번호 찾기
    @PostMapping("/pass/find")
    public ResponseEntity<ResponseDTO> findPass(@RequestParam("email") String email, @RequestParam("name") String name, @RequestParam("id") String userId) throws Exception {
        mailService.findPass(email, name, userId);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "입력한 회원 정보 일치"));
    }

    // 비밀번호 변경
    @PatchMapping("/pass/change")
    public ResponseEntity<ResponseDTO> changePass(@RequestParam("id") String userId, @RequestParam("password") String password, @RequestParam("passCheck") String passCheck) throws Exception {
        userService.changePass(userId, password, passCheck);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "비밀번호 변경 완료"));
    }

    // 비밀번호 확인
    @PostMapping("/pass/check")
    public ResponseEntity<ResponseDTO> checkPass(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam("password") String password) throws Exception {
        userService.checkPass(userPrincipal.getPassword(), password);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "비밀번호 일치"));
    }

    // 이메일 인증 보내기
    @PostMapping("/email/send")
    public ResponseEntity<ResponseDTO> sendEmail(@RequestParam("email") String email) throws Exception {
        mailService.sendSimpleMessage(email);
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "이메일 인증번호 전송"));
    }

    // 이메일 인증 확인
    @PostMapping("/email/check")
    public ResponseEntity<ResponseDTO> checkEmail(@RequestParam("email") String email, @RequestParam("code") String code) throws Exception {
        if (mailService.checkCode(email, code))
            return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.OK, "이메일 인증 완료"));
        return ResponseEntity.ok().body(ResponseDTO.of(HttpStatus.BAD_REQUEST, "이메일 인증 실패"));
    }


}