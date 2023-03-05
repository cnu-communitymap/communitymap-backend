package com.swacademy.mapcommunity.data.repository;

import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDataEntity, Long> {

}
