package com.swacademy.mapcommunity.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swacademy.mapcommunity.domain.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
