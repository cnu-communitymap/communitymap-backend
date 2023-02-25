package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

@Slf4j
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository repository;

    @BeforeEach
    void setUp(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("insert test")
    void testCustomer() {
        // Given
        Post post = new Post();
        post.setTitle("test post");
        post.setContent("this is test post in PostRepositoryTest.java");

        repository.save(post);   //SpringDataJpa

        //Then
        Post entity = repository.findById(post.getId()).get();
        log.info("title: {} , content: {}", entity.getTitle(), entity.getContent());
    }

}
