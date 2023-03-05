package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long savePost(Post post) {
        return postRepository.insertPost(post);
    }

}
