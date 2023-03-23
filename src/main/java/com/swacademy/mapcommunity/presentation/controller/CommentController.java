package com.swacademy.mapcommunity.presentation.controller;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.service.CommentService;
import com.swacademy.mapcommunity.domain.service.UserService;
import com.swacademy.mapcommunity.presentation.dto.CommentDto;
import com.swacademy.mapcommunity.presentation.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentMapper commentMapper;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentMapper commentMapper, CommentService commentService, UserService userService) {
        this.commentMapper = commentMapper;
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping(value ="/write")
    public ResponseEntity<Long> create(@RequestBody CommentDto commentDto) {
        Comment entity = commentMapper.toEntity(commentDto);
        commentService.saveComment(entity, entity.getPost().getId(), userService.getLoggedInUser());
        return ResponseEntity.ok(entity.getId());
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Long> update(@RequestBody CommentDto commentDto) {
        Long commentId = commentService.updateComment(commentMapper.toEntity(commentDto));
        return ResponseEntity.ok(commentId);
    }

    @GetMapping(value = "/read")
    public CommentDto read(@RequestParam("commentId") Long commentId) {
        Comment entity = commentService.getCommentById(commentId);
        return commentMapper.toDto(entity);
    }

    @GetMapping(value = "/delete")
    public void delete(@RequestParam("commentId") Long commentId){
        commentService.deleteCommentById(commentId);
    }

}
