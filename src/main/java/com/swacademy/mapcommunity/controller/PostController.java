package com.swacademy.mapcommunity.controller;

import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.presentation.dto.PostDto;
import com.swacademy.mapcommunity.presentation.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("post")
public class PostController {

    private final PostMapper postMapper;

    @Autowired
    public PostController(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Long> register(@RequestBody PostDto postDto) {
        Post entity = postMapper.toEntity(postDto);

        return ResponseEntity.ok(entity.getId());
    }
}
