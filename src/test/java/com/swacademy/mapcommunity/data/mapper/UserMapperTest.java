package com.swacademy.mapcommunity.data.mapper;

import com.swacademy.mapcommunity.MapcommunityBackendApplication;

import com.swacademy.mapcommunity.data.entity.CommentDataEntity;
import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MapcommunityBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class UserMapperTest {

    private final ModelMapper modelMapper = new ModelMapper();
    private final UserMapper userMapper = new UserMapper(modelMapper);

    @Test
    @DisplayName("Object toDataEntity mapper test with User, Post")
    public void testToDataEntity() {
        //Given
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setPassword("test123");
        user.setNickname("tester");
        user.setGender(com.swacademy.mapcommunity.domain.entity.Gender.MALE);
        user.setBirth(LocalDate.of(2000, 1, 1));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("post title 1");
        post1.setContent("post content 1");
        post1.setPosition(new Location(36.369934530965246, 127.34573990100932));
        post1.setCreatedAt(LocalDateTime.now());
        post1.setUpdatedAt(LocalDateTime.now());

        Post post2 = new Post();
        post2.setId(2L);
        post2.setPosition(new Location(36.36637670516358, 127.34198023051803 ));
        post2.setTitle("post title 2");
        post2.setContent("post content 2");
        post2.setCreatedAt(LocalDateTime.now());
        post2.setUpdatedAt(LocalDateTime.now());

        post1.setUser(user);
        post2.setUser(user);

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        user.setPosts(posts);

        //When
        UserDataEntity userDataEntity = userMapper.toDataEntity(user);

        //Then
        assertNotNull(userDataEntity);
        assertEquals(user.getId(), userDataEntity.getId());
        assertEquals(user.getEmail(), userDataEntity.getEmail());
        assertEquals(user.getPassword(), userDataEntity.getPassword());
        assertEquals(user.getNickname(), userDataEntity.getNickname());
        assertEquals(user.getGender().toString(), userDataEntity.getGender().toString());
        assertEquals(user.getCreatedAt(), userDataEntity.getCreatedAt());
        assertEquals(user.getUpdatedAt(), userDataEntity.getUpdatedAt());
        assertEquals(user.getBirth(), userDataEntity.getBirth());
        assertEquals(user.getPosts().size(), userDataEntity.getPosts().size());
    }

    @Test
    @DisplayName("Object toEntity mapper test with UserDataEntity, PostDataEntity")
    public void testToEntity() {
        // Given
        UserDataEntity userDataEntity = new UserDataEntity();
        userDataEntity.setId(1L);
        userDataEntity.setEmail("test@test.com");
        userDataEntity.setPassword("password");
        userDataEntity.setNickname("nickname");
        userDataEntity.setGender(com.swacademy.mapcommunity.data.entity.Gender.MALE);
        userDataEntity.setBirth(LocalDate.of(2000, 1, 1));
        userDataEntity.setCreatedAt(LocalDateTime.now());
        userDataEntity.setUpdatedAt(LocalDateTime.now());

        PostDataEntity postDataEntity1 = new PostDataEntity();
        postDataEntity1.setId(1L);
        postDataEntity1.setTitle("post title 1");
        postDataEntity1.setContent("post content 1");
        postDataEntity1.setCreatedAt(LocalDateTime.now());
        postDataEntity1.setUpdatedAt(LocalDateTime.now());
        postDataEntity1.setPosition(new Location(36.369934530965246, 127.34573990100932).asPoint());

        PostDataEntity postDataEntity2 = new PostDataEntity();
        postDataEntity2.setId(2L);
        postDataEntity2.setTitle("post title 2");
        postDataEntity2.setContent("test tests");
        postDataEntity2.setCreatedAt(LocalDateTime.now());
        postDataEntity2.setUpdatedAt(LocalDateTime.now());
        postDataEntity2.setPosition(new Location(36.369934530965246, 127.34573990100932).asPoint());

        postDataEntity1.setUser(userDataEntity);
        postDataEntity2.setUser(userDataEntity);

        //When
        User user = userMapper.toEntity(userDataEntity);

        //Then
        assertNotNull(userDataEntity);
        assertEquals(user.getId(), userDataEntity.getId());
        assertEquals(user.getEmail(), userDataEntity.getEmail());
        assertEquals(user.getPassword(), userDataEntity.getPassword());
        assertEquals(user.getNickname(), userDataEntity.getNickname());
        assertEquals(user.getGender().toString(), userDataEntity.getGender().toString());
        assertEquals(user.getCreatedAt(), userDataEntity.getCreatedAt());
        assertEquals(user.getUpdatedAt(), userDataEntity.getUpdatedAt());
        assertEquals(user.getBirth(), userDataEntity.getBirth());
    }

    @Test
    @DisplayName("Object User mapper toEntity test with User, Post, Comment")
    public void toEntity_FK() {
        //Given
        //Create User object to convert
        UserDataEntity userDataEntity = new UserDataEntity();
        userDataEntity.setId(1L);
        userDataEntity.setEmail("test@test.com");
        userDataEntity.setPassword("password");
        userDataEntity.setNickname("nickname");
        userDataEntity.setGender(com.swacademy.mapcommunity.data.entity.Gender.MALE);
        userDataEntity.setBirth(LocalDate.of(2000, 1, 1));
        userDataEntity.setCreatedAt(LocalDateTime.now());
        userDataEntity.setUpdatedAt(LocalDateTime.now());

        //Create Post object to convert
        PostDataEntity postDataEntity1 = new PostDataEntity();
        postDataEntity1.setId(1L);
        postDataEntity1.setTitle("post title 1");
        postDataEntity1.setContent("post content 1");
        postDataEntity1.setCreatedAt(LocalDateTime.now());
        postDataEntity1.setUpdatedAt(LocalDateTime.now());
        postDataEntity1.setPosition(new Location(36.369934530965246, 127.34573990100932).asPoint());

        PostDataEntity postDataEntity2 = new PostDataEntity();
        postDataEntity2.setId(2L);
        postDataEntity2.setTitle("post title 2");
        postDataEntity2.setContent("test tests");
        postDataEntity2.setCreatedAt(LocalDateTime.now());
        postDataEntity2.setUpdatedAt(LocalDateTime.now());
        postDataEntity2.setPosition(new Location(36.369934530965246, 127.34573990100932).asPoint());

        postDataEntity1.setUser(userDataEntity);
        postDataEntity2.setUser(userDataEntity);

        //Create Comment object to convert
        CommentDataEntity commentDataEntity = new CommentDataEntity();
        commentDataEntity.setId(1L);
        commentDataEntity.setContent("hi");
        commentDataEntity.setCommentLike(0);
        commentDataEntity.setUser(userDataEntity);
        commentDataEntity.setPost(postDataEntity1);
        commentDataEntity.setPost(postDataEntity2);

        commentDataEntity.setUser(userDataEntity);

        //When
        User user = userMapper.toEntity(userDataEntity);

        //Then
        assertNotNull(user);
        assertNotNull(userDataEntity);
        assertEquals(user.getId(), userDataEntity.getId());
        assertEquals(user.getEmail(), userDataEntity.getEmail());
        assertEquals(user.getPassword(), userDataEntity.getPassword());
        assertEquals(user.getNickname(), userDataEntity.getNickname());
        assertEquals(user.getGender().toString(), userDataEntity.getGender().toString());
        assertEquals(user.getCreatedAt(), userDataEntity.getCreatedAt());
        assertEquals(user.getUpdatedAt(), userDataEntity.getUpdatedAt());
        assertEquals(user.getBirth(), userDataEntity.getBirth());

    }

}