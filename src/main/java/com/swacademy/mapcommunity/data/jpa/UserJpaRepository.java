package com.swacademy.mapcommunity.data.jpa;

import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDataEntity, Long> {
    UserDataEntity findByEmail(String email);
}
