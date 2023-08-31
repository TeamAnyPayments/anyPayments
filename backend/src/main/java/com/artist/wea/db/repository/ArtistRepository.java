package com.artist.wea.db.repository;

import com.artist.wea.db.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findByNameContaining(String name);

}
