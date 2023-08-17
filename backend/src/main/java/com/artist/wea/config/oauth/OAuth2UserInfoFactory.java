package com.artist.wea.config.oauth;

import com.artist.wea.config.oauth.userinfo.KakaoOAuth2UserInfo;
import com.artist.wea.config.oauth.userinfo.NaverOAuth2UserInfo;
import com.artist.wea.config.oauth.userinfo.OAuth2UserInfo;
import com.artist.wea.db.entity.base.SocialType;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(SocialType socialType, Map<String, Object> attributes) {
        switch (socialType) {
            case NAVER:
                return new NaverOAuth2UserInfo(attributes);
            case KAKAO:
                return new KakaoOAuth2UserInfo(attributes);
            default:
                throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }

}