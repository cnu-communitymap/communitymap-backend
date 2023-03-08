package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.MapcommunityBackendApplication;
import com.swacademy.mapcommunity.data.repository.PostJpaRepository;
import com.swacademy.mapcommunity.data.repository.UserJpaRepository;
import com.swacademy.mapcommunity.domain.entity.Gender;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
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

    @BeforeAll
    public void setup() throws IOException {
        newUser = new User();
        newUser.setEmail("purplepig4657@gmail.com");
        newUser.setPassword("asdf");
        newUser.setNickname("purplepig");
        newUser.setGender(Gender.MALE);
        newUser.setBirth(LocalDate.now());

        userService.saveUser(newUser);

        double latitude = 32.123;
        double longitude = 127.123;
        Point point = new GeometryFactory().createPoint(new Coordinate(latitude, longitude));

        newPost = new Post();
        newPost.setUser(newUser);
        newPost.setTitle("asdf");
        newPost.setContent("asdf");
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setPosition(new Location(1, 1));

//        postService.savePost(newPost);
    }

    @Test
    public void selectTest() throws IOException {
        User referenceById = userService.getUserById(newUser.getId());
        System.out.println(referenceById.getPosts());

        double latitude = 32.123;
        double longitude = 127.123;
        Point point = new GeometryFactory().createPoint(new Coordinate(latitude, longitude));

        Post newPost = new Post();
        newPost.setUser(newUser);
        newPost.setTitle("asdfsadf");
        newPost.setContent("asdf");
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setPosition(new Location(1, 1));

//        postService.savePost(newPost);

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
