package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
