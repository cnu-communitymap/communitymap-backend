package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.MapcommunityBackendApplication;
import com.swacademy.mapcommunity.domain.entity.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = MapcommunityBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class PostServiceTest2 {

    @Autowired
    PostService postService;

    @Test
    public void test() {
        Post post = new Post();
        post.setPostLike(1000000);
        System.out.println(postService.savePost(post));
        try {
        } catch (IllegalArgumentException e) {}
    }

}
