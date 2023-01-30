package com.swacademy.mapcommunity.domain.post;

import com.swacademy.mapcommunity.data.PostRepository;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.vo.Position;
import com.swacademy.mapcommunity.domain.vo.PostContent;
import com.swacademy.mapcommunity.domain.vo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // @TODO createPost 함수가 필요한지 생각해보기.

    public Post registerPost(UserInfo user, PostContent postContent, Position position) {
        // @TODO ADD Authorization logic
        // @TODO 일단 Authorization은 고려하지 않고 구현한다.
        // @TODO UUID.randomUUID -> 현재 UUID를 MySQL의 Primary Key에 맞춰서 생성해야 하는지 감이 잡히지 않아 임시 구현. 바꿔야 함.
        // @TODO postRepository.registerPost의 구현도 다시 생각해봐야 함. Post를 생성해서 넘겨야 하는지 내용만 넘기는지.
        Post newPost = new Post(UUID.randomUUID(), user, postContent, position);
        return postRepository.registerPost(newPost);
    }

    public Post getPost(UUID postId) {
        return postRepository.getPost(postId);
    }

    /**
     * @return Like is successfully added or not.
     */
    boolean upLike(UUID postId) {
        // @TODO ADD Authorization logic
        return postRepository.upLike(postId);
    }
}
