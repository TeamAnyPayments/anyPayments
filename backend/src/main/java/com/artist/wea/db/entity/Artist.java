package com.artist.wea.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "artist", indexes = {@Index(name = "idx_artist_name", columnList = "artist_name")})
//@Table(name = "artist")
@Entity
public class Artist {

    @Id
    @Column(name = "artist_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist_name", nullable = false, unique = true)
    private String name;

    @Column(name = "artist_simple")
    private String simple;

    @Column(name = "artist_introduce")
    private String introduce;

    @Column(name = "artist_area")
    private String area;

    @Column(name = "artist_activated")
    private boolean activated;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "artist")
    private List<ArtistMember> members = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "artist_img_id", referencedColumnName = "artist_img_id")
    private ArtistImg artistImg;

    @OneToOne
    @JoinColumn(name = "artist_bg_img_id", referencedColumnName = "artist_bg_img_id")
    private ArtistBgImg artistBgImg;

    @Builder
    public Artist(String name, String simple, String introduce, String area){
        this.name = name;
        this.simple = simple;
        this.introduce = introduce;
        this.area = area;
    }

}
