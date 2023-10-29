package com.artist.wea.db.entity;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "artist_member")
@Entity
public class ArtistMember {

    @Id
    @Column(name = "artist_member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column(name = "artist_member_activated", nullable = false)
    private Boolean activated;

    public ArtistMember(User user, Artist artist, Boolean activated){
        this.user = user;
        this.artist = artist;
        this.activated = activated;
    }

}
