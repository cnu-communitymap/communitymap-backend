package com.swacademy.mapcommunity.controller;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.presentation.dto.UserDto;
import com.swacademy.mapcommunity.presentation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    UserMapper userMapper;

    public UserController(@Autowired UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Long> register(@RequestBody UserDto userDto) {

        User entity = userMapper.toEntity(userDto);

        return ResponseEntity.ok(entity.getId());
    }
}
