package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.exception.InternalServerException;
import com.swacademy.mapcommunity.domain.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

//    public Long saveComment(Comment comment, Long savedPostId, User writer)
//            throws IllegalArgumentException, InternalServerException {
//        Post savedPost = this.postService.getPostById(savedPostId);
//        comment.
//    }

}
