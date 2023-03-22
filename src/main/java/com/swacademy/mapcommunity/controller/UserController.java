package com.swacademy.mapcommunity.controller;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.service.UserService;
import com.swacademy.mapcommunity.presentation.dto.UserDto;
import com.swacademy.mapcommunity.presentation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    //@Todo Long -> UUID
    @PostMapping(value = "/signup")
    public ResponseEntity<Long> create(@RequestBody UserDto userDto) {
        Long userId = userService.saveUser(userMapper.toEntity(userDto));
        return ResponseEntity.ok(userId);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Long> update(@RequestBody UserDto userDto) throws IOException {
        User loggedUser = userService.getLoggedInUser();
        userDto.setId(loggedUser.getId());
        Long userId = userService.updateUser(userMapper.toEntity(userDto));
        return ResponseEntity.ok(userId);
    }

    @GetMapping(value = "/mypage")
    public UserDto read() {
        User loggedUser = userService.getLoggedInUser();
        User entity = userService.getUserById(loggedUser.getId());
        return userMapper.toDto(entity);
    }

    @GetMapping(value = "/delete")
    public Boolean delete() throws IOException {
        User loggedUser = userService.getLoggedInUser();
        userService.deleteUserById(loggedUser.getId());
        return true;
    }
}
