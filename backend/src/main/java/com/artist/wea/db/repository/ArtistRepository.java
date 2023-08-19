package com.artist.wea.db.repository;

import com.artist.wea.db.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {


}
