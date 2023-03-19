package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.MapcommunityBackendApplication;
import com.swacademy.mapcommunity.data.entity.CommentDataEntity;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest(classes = MapcommunityBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class PostServiceTest2 {

    @Autowired
    PostService postService;

    @Autowired
    ModelMapper modelMapper;

    @Test
    public void test() {
        //Create User object to convert using FK
        User user = new User();
        user.setId(20200404L);
        user.setEmail("sa@gmail.com");
        user.setGender(com.swacademy.mapcommunity.domain.entity.Gender.MALE);
        user.setPassword("sasa123");
        user.setNickname("tester");
        user.setBirth(LocalDate.of(2000, 1, 1));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        //Create Post Object to convert using FK
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Title");
        post.setPostLike(0);
        post.setContent("this is test");
        post.setPosition(new Location(37.123456, 127.123456));
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        post.setUser(user);

        //Create Comment Object to Convert
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("test comment");
        comment.setCommentLike(0);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        comment.setUser(user);
        comment.setPost(post);

        CommentDataEntity commentDataEntity = modelMapper.map(comment, CommentDataEntity.class);

        System.out.println(commentDataEntity.getPost().getComments().get(0).getContent());
    }

}
