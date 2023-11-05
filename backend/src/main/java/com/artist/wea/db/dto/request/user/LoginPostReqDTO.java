package com.artist.wea.db.dto.request.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginPostReqDTO {

    private String id;

    private String password;

}