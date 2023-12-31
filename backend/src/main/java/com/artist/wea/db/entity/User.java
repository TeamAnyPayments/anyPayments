package com.artist.wea.db.entity;

import com.artist.wea.db.entity.base.BaseTime;
import com.artist.wea.db.entity.base.Role;
import com.artist.wea.db.entity.base.SocialType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User extends BaseTime {

    @Id
    @Column(name = "user_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "user_id", nullable = false, unique = true)
    private String id;

    @JsonIgnore
    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_terms", nullable = false)
    private boolean terms;

    @JsonIgnore
    @Column(name = "user_activated", nullable = false)
    private boolean activated;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    @OneToOne
    @JoinColumn(name = "user_img_id", referencedColumnName = "user_img_id")
    private UserImg userImg; // 유저 이미지

}