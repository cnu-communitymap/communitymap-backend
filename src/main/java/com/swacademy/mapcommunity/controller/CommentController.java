package com.swacademy.mapcommunity.controller;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.presentation.dto.CommentDto;
import com.swacademy.mapcommunity.presentation.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentController(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @PostMapping(value ="/register")
    public ResponseEntity<Long> register(@RequestBody CommentDto commentDto) {
        Comment entity = commentMapper.toEntity(commentDto);

        return ResponseEntity.ok(entity.getId());
    }

}
