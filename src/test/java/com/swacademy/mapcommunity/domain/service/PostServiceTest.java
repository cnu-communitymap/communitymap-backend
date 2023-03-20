package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.MapcommunityBackendApplication;
import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import com.swacademy.mapcommunity.data.jpa.PostJpaRepository;
import com.swacademy.mapcommunity.data.jpa.UserJpaRepository;
import com.swacademy.mapcommunity.domain.entity.Gender;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest(classes = MapcommunityBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class PostServiceTest {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    PostJpaRepository postRepository;
    @Autowired
    UserJpaRepository userJpaRepository;

    private Post newPost;
    private User newUser;

    private UserDataEntity userData;

    @BeforeAll
    @Transactional
    public void setup() {
        newUser = new User();
        newUser.setEmail("purplepig4657@gmail.com");
        newUser.setPassword("asdf");
        newUser.setNickname("purplepig");
        newUser.setGender(Gender.MALE);
        newUser.setBirth(LocalDate.now());

        Long userId = userService.saveUser(newUser);

        System.out.println(userId);

        newUser = this.userService.getUserById(userId);
        UserDataEntity userDataEntity = this.userJpaRepository.getReferenceById(newUser.getId());
        Hibernate.initialize(userDataEntity);
        System.out.println(userDataEntity.getEmail());

        System.out.println(newUser.getEmail());
        System.out.println(newUser.getPosts());

        double latitude = 32.123;
        double longitude = 127.123;
        Point point = new GeometryFactory().createPoint(new Coordinate(latitude, longitude));

        newPost = new Post();
        newPost.setUser(newUser);
        newPost.setTitle("asdf");
        newPost.setContent("asdf");
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setPosition(new Location(1, 1));

        postService.savePost(newPost, newUser);
    }

    @Test
    public void selectTest() {
        User referenceById = userService.getUserById(newUser.getId());
//        UserDataEntity userDataEntity = this.userJpaRepository.getReferenceById(newUser.getId());
//        System.out.println(userDataEntity);
//        System.out.println(userDataEntity.getEmail());
//        System.out.println(referenceById.getPosts());

        double latitude = 32.123;
        double longitude = 127.123;
        Point point = new GeometryFactory().createPoint(new Coordinate(latitude, longitude));

        Post newPost = new Post();
        newPost.setUser(newUser);
        newPost.setTitle("asdfsadf");
        newPost.setContent("asdf");
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setPosition(new Location(1, 1));

        postService.savePost(newPost, newUser);

        System.out.println(newUser.getPosts());

        referenceById = userService.getUserById(newUser.getId());
        System.out.println(referenceById.getPosts());
    }

    @Test
    public void insertTest() {
        User referenceById = userService.getUserById(newUser.getId());
        System.out.println(referenceById.getPosts());
    }

    @Test
    public void updateTest() throws IOException {
        User referenceById = userService.getUserById(newUser.getId());
        referenceById.setNickname("hello");
        userService.updateUser(referenceById);

        referenceById = userService.getUserById(newUser.getId());
        System.out.println(referenceById.getPosts());
    }

}
