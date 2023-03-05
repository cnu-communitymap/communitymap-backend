package com.swacademy.mapcommunity.data.repository;

import com.swacademy.mapcommunity.data.entity.CommentDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentDataEntity, Long> {

}
