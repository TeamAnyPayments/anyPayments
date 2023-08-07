package com.artist.wea.db.repository;

import com.artist.wea.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

}
