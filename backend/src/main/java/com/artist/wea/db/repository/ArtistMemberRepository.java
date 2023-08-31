package com.artist.wea.db.repository;

import com.artist.wea.db.entity.ArtistMember;
import com.artist.wea.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistMemberRepository extends JpaRepository<ArtistMember, Long> {

    ArtistMember findByUser(User user);

}
