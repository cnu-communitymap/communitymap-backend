package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.aop.InternalServerExceptionConverter;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.exception.InternalServerException;
import com.swacademy.mapcommunity.domain.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    @InternalServerExceptionConverter
    public Long saveComment(Comment comment, Long savedPostId, User writer)
            throws IllegalArgumentException, InternalServerException {
        Post savedPost = this.postService.getPostById(savedPostId);
        comment.setUser(writer);  // @TODO Change this code that using spring security.
        comment.setPost(savedPost);
        return commentRepository.insertComment(comment);
    }

    @InternalServerExceptionConverter
    public Comment getCommentById(Long commentId) throws IllegalArgumentException {
        return commentRepository.selectCommentById(commentId);
    }

    @InternalServerExceptionConverter
    public Comment getCommentWithUserAndPostById(Long commentId) throws IllegalArgumentException {
        return commentRepository.selectCommentById(commentId, true, true);
    }

    @InternalServerExceptionConverter
    public List<Comment> getCommentsByPostId(Long postId) throws IllegalArgumentException {
        return postService.getCommentsByPostId(postId);
    }

    @InternalServerExceptionConverter
    public User getWriterByCommentId(Long commentId) throws IllegalArgumentException {
        return commentRepository.selectUserByCommentId(commentId);
    }

    @InternalServerExceptionConverter
    public Post getPostByCommentId(Long commentId) throws IllegalArgumentException {
        return commentRepository.selectPostByCommentId(commentId);
    }

    @InternalServerExceptionConverter
    public Long updateComment(Comment updatedComment) throws IllegalArgumentException, InternalServerException {
        // @TODO Add authentication logic.
        return commentRepository.updateComment(updatedComment);
    }

    @InternalServerExceptionConverter
    public boolean deleteCommentById(Long commentId) throws IllegalArgumentException, InternalServerException {
        // @TODO Add authentication logic.
        return commentRepository.deleteCommentById(commentId);
    }

}
