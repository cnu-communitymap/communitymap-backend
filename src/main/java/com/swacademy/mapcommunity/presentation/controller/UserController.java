package com.swacademy.mapcommunity.presentation.controller;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.service.PostService;
import com.swacademy.mapcommunity.domain.service.UserService;
import com.swacademy.mapcommunity.presentation.dto.CommentDto;
import com.swacademy.mapcommunity.presentation.dto.PostDto;
import com.swacademy.mapcommunity.presentation.dto.UserDto;
import com.swacademy.mapcommunity.presentation.mapper.CommentMapper;
import com.swacademy.mapcommunity.presentation.mapper.PostMapper;
import com.swacademy.mapcommunity.presentation.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final PostService postService;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService, PostService postService, PostMapper postMapper, CommentMapper commentMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.postService = postService;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
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
    public List<PostDto> readPosts() {
        User loggedUser = userService.getLoggedInUser();
        List<Post> posts = userService.getUserPostsByUserId(loggedUser.getId());
        return posts.stream()
                .map(post -> {
                    PostDto postDto = postMapper.toDto(post);
                    postDto.setImageUrl(postService.getImageUrl(post));
                    return postDto;
                })
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/comments")
    public List<CommentDto> readComments() {
        User loggedUser = userService.getLoggedInUser();

        List<Comment> comments = userService.getUserCommentsByUserId(loggedUser.getId());
        return comments.stream().map(commentMapper::toDto).collect(Collectors.toList());  //Entity -> Dto
    }

    @GetMapping(value = "/delete")
    public Boolean delete() throws IOException {
        User loggedUser = userService.getLoggedInUser();
        userService.deleteUserById(loggedUser.getId());
        return true;
    }
}
