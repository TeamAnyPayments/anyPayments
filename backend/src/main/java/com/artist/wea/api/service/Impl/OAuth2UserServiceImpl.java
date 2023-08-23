package com.artist.wea.api.service.Impl;

import com.artist.wea.config.oauth.OAuth2UserInfoFactory;
import com.artist.wea.config.oauth.userinfo.OAuth2UserInfo;
import com.artist.wea.config.security.UserPrincipal;
import com.artist.wea.db.entity.User;
import com.artist.wea.db.entity.base.Role;
import com.artist.wea.db.entity.base.SocialType;
import com.artist.wea.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        SocialType socialType = SocialType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(socialType, user.getAttributes());
        User savedUser = createUser(userInfo, socialType);

        return UserPrincipal.create(savedUser, user.getAttributes());
    }

    private User createUser(OAuth2UserInfo userInfo, SocialType socialType) {
        User user = User.builder()
                .id(userInfo.getId())
                .email(UUID.randomUUID() + "@socialUser.com")
                .name(userInfo.getNickname())
                .password(UUID.randomUUID() + "password")
                .terms(true)
                .activated(true)
                .socialId(userInfo.getId())
                .role(Role.USER)
                .socialType(socialType)
                .build();

        return userRepository.save(user);
    }

}