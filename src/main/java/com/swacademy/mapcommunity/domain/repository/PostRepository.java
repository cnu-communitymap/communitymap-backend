package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.vo.Position;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository {
    Post insertPost(Post post);
    Post updatePost(Post post);
    void deletePost(UUID postID);
    // @TODO UUID는 일단 다시 생각해볼 필요가 있음. -> MySQL의 Primary Key를 넘겨줘야 할까?
    Optional<Post> getPostById(UUID postId);
    List<Post> getPostsByPosition(Position position);
}
