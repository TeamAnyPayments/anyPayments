package com.artist.wea.db.entity;

import com.artist.wea.db.entity.base.BaseTime;
import com.artist.wea.db.entity.base.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}