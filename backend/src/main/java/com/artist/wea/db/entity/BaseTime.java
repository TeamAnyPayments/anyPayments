package com.artist.wea.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseTime {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 생성 시간

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @LastModifiedDate
    private LocalDateTime modifiedAt; // 수정 시간

}