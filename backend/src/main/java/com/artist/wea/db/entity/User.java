package com.artist.wea.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTime implements UserDetails {

    @Id
    @Column(name = "user_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "user_id", nullable = false, unique = true)
    private String id;

    @JsonIgnore
    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_terms", nullable = false)
    private boolean terms;

    @JsonIgnore
    @Column(name = "user_activated", nullable = false)
    private boolean activated;

    /**
     * 계정이 가지고 있는 권한 목록을 리턴
     *
     * @return null
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 유저를 구분할 수 있는 아이디 리턴
     *
     * @return id
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.id;
    }

    /**
     * 계정이 만료되었는지 체크
     *
     * @return true
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정이 잠겼는지 체크
     *
     * @return true
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 계정의 비밀번호가 만료되었는지 체크
     *
     * @return true
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정이 사용가능한지 체크
     *
     * @return true
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

}