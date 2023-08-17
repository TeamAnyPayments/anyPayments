package com.artist.wea.api.service;

import com.artist.wea.db.dto.request.user.JoinPostReqDTO;
import com.artist.wea.db.dto.response.user.JoinPostResDTO;
import com.artist.wea.db.entity.User;

import java.util.Optional;

public interface UserService {

    JoinPostResDTO join(JoinPostReqDTO joinPostReqDto);

    String login(String userId, String password);

    void logout();

    Optional<User> getUser(String userId);

    boolean duplicateId(String userId);

    void changePass(String userId, String password, String passCheck);

    boolean checkPass(String password, String password1);

}
