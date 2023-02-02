package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.Post;

import java.util.UUID;

public interface PostRepository {
    Post registerPost(Post post);
    Post updatePost(Post post);
    // @TODO UUID는 일단 다시 생각해볼 필요가 있음. -> MySQL의 Primary Key를 넘겨줘야 할까?
    Post getPost(UUID postId);
}
