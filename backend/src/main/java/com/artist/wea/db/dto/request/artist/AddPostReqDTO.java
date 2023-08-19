package com.artist.wea.db.dto.request.artist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddPostReqDTO {

    private String name;

    private String simple;

    private String introduce;

    private String area;

}
