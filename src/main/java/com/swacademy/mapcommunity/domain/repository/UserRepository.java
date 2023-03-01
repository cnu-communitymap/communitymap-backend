package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.comments WHERE u.email = :email")
    Optional<User> findByEmail(String email) ;
}
