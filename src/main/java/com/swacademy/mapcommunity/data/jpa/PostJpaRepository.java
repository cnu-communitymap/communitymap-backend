package com.swacademy.mapcommunity.data.jpa;

import com.swacademy.mapcommunity.data.entity.Category;
import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<PostDataEntity, Long> {
    List<PostDataEntity> findAllByCategory(Category category);
}
