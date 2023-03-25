package com.swacademy.mapcommunity.presentation.controller;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.service.UserService;
import com.swacademy.mapcommunity.presentation.dto.UserDto;
import com.swacademy.mapcommunity.presentation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @GetMapping(value ="/posts")
    public List<Post> readPosts() {
        User loggedUser = userService.getLoggedInUser();

        return userService.getUserPostsByUserId(loggedUser.getId());
    }

    @GetMapping(value = "/comments")
    public List<Comment> readComments() {
        User loggedUser = userService.getLoggedInUser();

        return userService.getUserCommentsByUserId(loggedUser.getId());
    }

    @GetMapping(value = "/delete")
    public Boolean delete() throws IOException {
        User loggedUser = userService.getLoggedInUser();
        userService.deleteUserById(loggedUser.getId());
        return true;
    }
}
