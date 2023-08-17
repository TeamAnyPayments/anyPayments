package com.artist.wea.db.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FindIdPostReqDTO {

    String name;
    String email;

}
