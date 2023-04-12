package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.modelmapper.MappingException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.swacademy.mapcommunity.MapcommunityBackendApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        user.setId(1L);
        user.setEmail("cnu@gmail.com");
        user.setPassword("1234");
        user.setNickname("cnu_student");
        user.setGender(Gender.NONE);
        user.setBirth(LocalDate.of(2002, 1, 1));
    }

    @Test
    @Order(0)
    @DisplayName("insert User")
    public void insertUserTest() {
        // When
        Long insertedUserId = userRepositoryJpaImpl.insertUser(user);

        // Then
        assertEquals(insertedUserId, user.getId());   //@Todo 현재 GENERATE.Type=AUTO   -> UUID로 변경시 코드 변경
    }

    @Test
    @Order(1)
    @DisplayName("select User By Id")
    public void selectUserByIdTest() {

        // When
        User selectedUser = userRepositoryJpaImpl.selectUserById(user.getId());

        // Then
        assertEquals(selectedUser.getId(), user.getId());  //@Todo UUID로 변경 시 코드 변경 필요
        assertEquals(selectedUser.getPassword(), user.getPassword());
        assertEquals(selectedUser.getEmail(), user.getEmail());
        assertEquals(selectedUser.getNickname(), user.getNickname());
        assertEquals(selectedUser.getBirth(), user.getBirth());
    }


    @Test
    @Order(2)
    @DisplayName("update User")
    public void updatedUserTest() {
        // Given
        // set updatedUser
        user.setEmail("changedEmail@gmail.com");
        user.setPassword("changedPassword");
        user.setNickname("chagnedNickName");
        user.setGender(Gender.FEMALE);
        user.setBirth(LocalDate.of(2000, 12, 1));

        // When
        Long updatedUserId = userRepositoryJpaImpl.updateUser(user);

        // Then
        assertEquals(updatedUserId, user.getId());          //@Todo 현재 GENERATE.Type=AUTO   -> UUID로 변경시 코드 변경

        User selectedUpdatedUser = userRepositoryJpaImpl.selectUserById(user.getId());
        assertEquals(selectedUpdatedUser.getNickname(), user.getNickname());
        assertEquals(selectedUpdatedUser.getBirth(), user.getBirth());
        assertEquals(selectedUpdatedUser.getEmail(), user.getEmail());
        assertEquals(selectedUpdatedUser.getPassword(), user.getPassword());
    }



    @Test
    @Order(3)
    @DisplayName("delete User")
    public void deleteUserById() {
        // Given
        User selectedUser = userRepositoryJpaImpl.selectUserById(user.getId());
        assertNotNull(selectedUser);    //It should exist when selectedUser by userId.

        // When
        userRepositoryJpaImpl.deleteUserById(user.getId());

        // Then
        // When userId is selectedUser, it is null and should be excluded.
        assertThrows(MappingException.class, () -> userRepositoryJpaImpl.selectUserById(user.getId()));
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @Order(4)
    @DisplayName("insert User")
    public void insertUserWithPostCommentTest() {
        //Given
        user.setId(2L);   //또 다른 user로 샹송
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

        user.addPost(post);
        user.addComment(comment);

        // When
        Long insertedUserId = userRepositoryJpaImpl.insertUser(user);

        // Then
        assertEquals(insertedUserId, user.getId());   //@Todo 현재 GENERATE.Type=AUTO   -> UUID로 변경시 코드 변경
    }

    @Test
    @Order(5)
    @DisplayName("select User By Id have posts or comments")
    public void selectUserByIdTest2() {
        // When
        User selectedUser = userRepositoryJpaImpl.selectUserById(user.getId(), true, true);

        // Then
        assertEquals(selectedUser.getId(), 2L);  //@Todo UUID로 변경 시 코드 변경 필요   //두 번째 들어가는거라 2
        assertEquals(selectedUser.getPassword(), user.getPassword());
        assertEquals(selectedUser.getEmail(), user.getEmail());
        assertEquals(selectedUser.getNickname(), user.getNickname());
        assertEquals(selectedUser.getBirth(), user.getBirth());
    }

}
