package com.artist.wea.db.entity.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "게스트"),
    USER("ROLE_USER", "사용자"),
    ARTIST("ROLE_ARTIST", "아티스트");

    private final String key;
    private final String title;
}