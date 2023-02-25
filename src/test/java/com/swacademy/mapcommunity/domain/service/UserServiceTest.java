package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import com.swacademy.mapcommunity.domain.vo.Gender;
import com.swacademy.mapcommunity.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@SpringBootTest
class UserServiceTest {

    //ogger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    UserDto userDto = UserDto.builder()
            .id(UUID.randomUUID())
            .email("serviceTest@Gmail.com")
            .password("1234!")
            .nickName("ServiceTest")
            .gender(Gender.NONE)
            .birth(LocalDate.of(1982, 02, 19))
            .build();

    @Test
    void save() {
        User user = modelMapper.map(userDto, User.class);

        User entity = userRepository.save(user);

        log.info(entity.getId().toString());

    }
}