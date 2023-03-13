package com.swacademy.mapcommunity.data.mapper;

import com.swacademy.mapcommunity.MapcommunityBackendApplication;
import com.swacademy.mapcommunity.data.entity.CommentDataEntity;
import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import com.swacademy.mapcommunity.domain.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MapcommunityBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class CommentMapperTest {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentMapperTest(ModelMapper modelMapper) {
        this.commentMapper = new CommentMapper(modelMapper);
    }

    @Test
    @DisplayName("Object toDataEntity mapper test with User, Post")
    public void testToDataEntity() {
        //Given
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

        //When
        CommentDataEntity commentDataEntity = commentMapper.toDataEntity(comment);

        //Then
        assertEquals(comment.getId(), commentDataEntity.getId());
        assertEquals(comment.getCommentLike(), commentDataEntity.getCommentLike());
        assertEquals(comment.getContent(), commentDataEntity.getContent());
        assertEquals(comment.getUser().getId(), commentDataEntity.getUser().getId());
        assertEquals(comment.getPost().getId(), commentDataEntity.getPost().getId());
        System.out.println("Comment 의 fk User id: "+comment.getUser().getId()+"="+commentDataEntity.getUser().getId());
        System.out.println("Comment 의 fk Post id: "+comment.getPost().getId()+"="+commentDataEntity.getPost().getId());
    }

    @Test
    @DisplayName("Object testToEntity mapper test with UserDataEntity, PostDataEntity, CommentDataEntity")
    public void testToEntity() {
        //Given
        //Create UserDataEntity object to convert using fk
        UserDataEntity userDataEntity1 = new UserDataEntity();
        userDataEntity1.setId(20190201L);
        userDataEntity1.setGender(com.swacademy.mapcommunity.data.entity.Gender.NONE);
        userDataEntity1.setNickname("tomato");
        userDataEntity1.setEmail("toma@gmail.com");
        userDataEntity1.setPassword("toma123!");
        userDataEntity1.setBirth(LocalDate.of(2021, 2, 1));
        userDataEntity1.setCreatedAt(LocalDateTime.now());
        userDataEntity1.setUpdatedAt(LocalDateTime.now());

        //Create PostDataEntity object to convert using fk
        PostDataEntity postDataEntity1 = new PostDataEntity();
        postDataEntity1.setId(10L);
        postDataEntity1.setTitle("test title");
        postDataEntity1.setContent("this tis content");
        postDataEntity1.setPosition(new Location(37.123456, 127.123456).asPoint());
        postDataEntity1.setPostLike(0);
        postDataEntity1.setCreatedAt(LocalDateTime.now());
        postDataEntity1.setUpdatedAt(LocalDateTime.now());

        postDataEntity1.setUser(userDataEntity1);

        //Create CommentDataEntity object to convert using
        CommentDataEntity commentDataEntity = new CommentDataEntity();
        commentDataEntity.setId(1L);
        commentDataEntity.setCommentLike(0);
        commentDataEntity.setContent("commentcommentcomment");
        commentDataEntity.setCreatedAt(LocalDateTime.now());
        commentDataEntity.setUpdatedAt(LocalDateTime.now());

        commentDataEntity.setUser(userDataEntity1);
        commentDataEntity.setPost(postDataEntity1);

        //When
        Comment comment = commentMapper.toEntity(commentDataEntity);

        //Then
        assertEquals(comment.getId(), commentDataEntity.getId());
        assertEquals(comment.getCommentLike(), commentDataEntity.getCommentLike());
        assertEquals(comment.getContent(), commentDataEntity.getContent());
        assertEquals(comment.getUser().getId(), commentDataEntity.getUser().getId());
        assertEquals(comment.getPost().getId(), commentDataEntity.getPost().getId());
    }

}
