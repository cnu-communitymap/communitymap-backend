package com.swacademy.mapcommunity.presentation.controller;

import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.service.PostService;
import com.swacademy.mapcommunity.domain.service.UserService;
import com.swacademy.mapcommunity.presentation.dto.PostDto;
import com.swacademy.mapcommunity.presentation.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
public class PostController {

    private final PostMapper postMapper;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostMapper postMapper, PostService postService, UserService userService) {
        this.postMapper = postMapper;
        this.postService = postService;
        this.userService = userService;
    }

    //@Todo Long -> UUID
    @PostMapping(value = "/write")
    public ResponseEntity<Long> create(@RequestBody PostDto postDto) {
        Post entity = postMapper.toEntity(postDto);
        postService.savePost(postMapper.toEntity(postDto), userService.getLoggedInUser());
        return ResponseEntity.ok(entity.getId());
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Long> update(@RequestBody PostDto postDto) {
        Long postId = postService.updatePost(postMapper.toEntity(postDto));
        return ResponseEntity.ok(postId);
    }

    @GetMapping(value = "/read")
    public PostDto read(@RequestParam("postId") Long postId) {
        Post entity = postService.getPostById(postId);
        return postMapper.toDto(entity);
    }

    @GetMapping(value = "/delete")
    public void delete(@RequestParam("postId") Long postId) {
        postService.deletePostById(postId);
    }
}
