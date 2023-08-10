package com.artist.wea.db.repository;

import com.artist.wea.db.entity.User;
import com.artist.wea.db.entity.base.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);

    Optional<User> findByEmailAndName(String email, String name);

    Optional<User> findByIdAndEmailAndName(String id, String email, String name);

    boolean existsById(String id);

}
