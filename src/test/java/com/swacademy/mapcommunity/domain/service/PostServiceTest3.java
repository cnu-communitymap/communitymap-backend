package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.MapcommunityBackendApplication;
import com.swacademy.mapcommunity.data.entity.Gender;
import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import com.swacademy.mapcommunity.data.jpa.PostJpaRepository;
import com.swacademy.mapcommunity.data.jpa.UserJpaRepository;
import com.swacademy.mapcommunity.data.mapper.UserMapper;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest(classes = MapcommunityBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class PostServiceTest3 {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    PostJpaRepository postJpaRepository;
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    UserMapper userMapper;

    private Post newPost;
    private UserDataEntity newUser;

    @BeforeAll
    public void setup() {
        newUser = new UserDataEntity();
        newUser.setEmail("purplepig4657@gmail.com");
        newUser.setPassword("asdf");
        newUser.setNickname("purplepig");
        newUser.setGender(Gender.MALE);
        newUser.setBirth(LocalDate.now());

        Long userId = this.userJpaRepository.save(newUser).getId();

        System.out.println(userId);

        double latitude = 32.123;
        double longitude = 127.123;
        Point point = new GeometryFactory().createPoint(new Coordinate(latitude, longitude));

        User mappedUser = this.userMapper.toEntity(newUser);

        newPost = new Post();
        newPost.setUser(mappedUser);
        newPost.setTitle("asdf");
        newPost.setContent("asdf");
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setPosition(new Location(1, 1));

        newPost.setId(postService.savePost(newPost, mappedUser));
    }

    @Test
    public void test() {
//        UserDataEntity userDataEntity = this.userJpaRepository.getReferenceById(newUser.getId());
//        Hibernate.initialize(userDataEntity);
//        Hibernate.initialize(userDataEntity.getPosts());
//        Hibernate.initialize(userDataEntity.getComments());
//        System.out.println(userDataEntity.getEmail());

        PostDataEntity postDataEntity = this.postJpaRepository.getReferenceById(newPost.getId());
        Post post = this.postService.getPostById(newPost.getId());
        System.out.println(post.getComments());

        User user = this.userService.getUserById(newUser.getId());

        System.out.println(post.getContent());

        post.setContent("asdfasdf");

        postService.updatePost(post);

        Post updatedPost = this.postService.getPostById(newPost.getId());

        System.out.println(updatedPost.getContent());

    }

}
