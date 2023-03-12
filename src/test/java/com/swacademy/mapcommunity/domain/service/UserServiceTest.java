package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.MapcommunityBackendApplication;
import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import com.swacademy.mapcommunity.data.jpa.UserJpaRepository;
import com.swacademy.mapcommunity.domain.entity.Gender;
import com.swacademy.mapcommunity.domain.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.time.LocalDate;

@SpringBootTest(classes = MapcommunityBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("local")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    private UserJpaRepository userRepository;

    private User user;

    @BeforeAll
    public void setup() {
        user = new User();
        user.setEmail("purplepig4657@gmail.com");
        user.setPassword("asdf");
        user.setNickname("purplepig");
        user.setGender(Gender.MALE);
        user.setBirth(LocalDate.now());
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void insertTest() throws IOException {

        userService.saveUser(user);

        UserDataEntity insertedUser = userRepository.findById(1L).get();

        System.out.println(insertedUser.getNickname());
        System.out.println(insertedUser.getBirth());
        System.out.println(insertedUser.getGender());
        System.out.println(insertedUser.getCreatedAt());
        System.out.println(insertedUser.getBirth());
    }

}
