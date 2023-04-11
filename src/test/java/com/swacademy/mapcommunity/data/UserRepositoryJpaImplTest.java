package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.swacademy.mapcommunity.MapcommunityBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
public class UserRepositoryJpaImplTest {

    @Autowired
    private UserRepositoryJpaImpl userRepositoryJpaImpl;

    private static User user;

    @BeforeAll
    static void setUpTest() {
        // Given
        user = new User();
        user.setId(1L); //@Todo UUID.random으로 change
        user.setEmail("cnu@gmail.com");
        user.setPassword("1234");
        user.setNickname("cnu_student");
        user.setGender(Gender.NONE);
        user.setBirth(LocalDate.of(2002, 1, 1));
    }

    @Test
    @Order(0)
    @DisplayName("insert User test")
    public void insertUserTest() {
        //Given
        //Post
        Post post = new Post();
        post.setPostLike(0);
        post.setContent("test content");
        post.setPosition(new Location(37.123474, 127.123443));
        post.setPostedUserId(user.getId());
        post.setTitle("test title");
        post.setUser(user);

        //Comment
        Comment comment = new Comment();
        comment.setContent("test comment content");
        comment.setCommentLike(0);
        comment.setUser(user);
        comment.setPost(post);

        // When
        Long insertedUserId = userRepositoryJpaImpl.insertUser(user);

        // Then
        assertEquals(insertedUserId, 1L);   //@Todo 현재 GENERATE.Type=AUTO   -> UUID로 변경시 코드 변경
    }

}
