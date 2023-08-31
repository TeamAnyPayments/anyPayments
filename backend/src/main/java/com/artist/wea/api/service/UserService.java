package com.artist.wea.api.service;

import com.artist.wea.db.dto.request.user.JoinPostReqDTO;
import com.artist.wea.db.dto.response.user.JoinPostResDTO;
import com.artist.wea.db.entity.User;
import com.artist.wea.db.entity.UserImg;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface UserService {

    JoinPostResDTO join(JoinPostReqDTO joinPostReqDto);

    String login(String userId, String password);

    void logout(String token);

    Optional<User> getUser(String userId);

    boolean duplicateId(String userId);

    void changePass(String userId, String password, String passCheck);

    boolean checkPass(String password, String password1);

    User updateUser(User user);

    boolean validImgFile(MultipartFile multipartFile);

    UserImg getImage(String email);

    UserImg uploadImage(MultipartFile multipartFile) throws IOException;

    UserImg updateImage(MultipartFile multipartFile, User user) throws IOException;

    void deleteImage(String email);

}
