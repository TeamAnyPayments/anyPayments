package com.artist.wea.db.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "artist_bg_img")
@Entity
public class ArtistBgImg {

    @Id
    @Column(name = "artist_bg_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String fileUrl;

    @Builder
    public ArtistBgImg(String name, String type, String fileUrl){
        this.name = name;
        this.type = type;
        this.fileUrl = fileUrl;
    }

    public void updateArtistBgImg(MultipartFile multipartFile, String fileUrl) {
        this.name = multipartFile.getOriginalFilename();
        this.type = multipartFile.getContentType();
        this.fileUrl = fileUrl;
    }

}
