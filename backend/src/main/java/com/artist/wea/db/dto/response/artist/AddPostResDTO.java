package com.artist.wea.db.dto.response.artist;

import com.artist.wea.db.entity.base.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddPostResDTO {

    private int id;

    private String name;

    private String simple;

    private String introduce;

    private String area;

}