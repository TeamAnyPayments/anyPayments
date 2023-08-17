package com.artist.wea.db.dto.response.user;

import com.artist.wea.db.entity.base.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class JoinPostResDTO {

    private String id;

    private String name;

    private String email;

    private Role role;

}