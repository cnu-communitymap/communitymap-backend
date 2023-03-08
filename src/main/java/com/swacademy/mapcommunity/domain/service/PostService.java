package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.aop.InternalServerExceptionConverter;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.exception.InternalPersistenceException;
import com.swacademy.mapcommunity.domain.exception.InternalServerException;
import com.swacademy.mapcommunity.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    private final int ALLOWED_POST_RANGE = 100;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @InternalServerExceptionConverter
    public Long savePost(Post post, User writer) throws IllegalArgumentException, InternalServerException {
        post.setUser(writer);  // @TODO Change this code by using spring security.
        return postRepository.insertPost(post);
    }

    @InternalServerExceptionConverter
    public Post getPostById(Long postId) throws IllegalArgumentException {
        return postRepository.selectPostById(postId);
    }

    @InternalServerExceptionConverter
    public Post getPostWithUserById(Long postId) throws IllegalArgumentException {
        return postRepository.selectPostById(postId, true, false);
    }

    @InternalServerExceptionConverter
    public Post getPostWithCommentById(Long postId) throws IllegalArgumentException {
        return postRepository.selectPostById(postId, false, true);
    }

    @InternalServerExceptionConverter
    public Post getPostWithUserAndCommentById(Long postId) throws IllegalArgumentException {
        return postRepository.selectPostById(postId, true, true);
    }

    @InternalServerExceptionConverter
    public Long updatePost(Post updatedPost) throws IllegalArgumentException {
        // @TODO Add authentication logic.
        return postRepository.updatePost(updatedPost);
    }

    @InternalServerExceptionConverter
    public boolean deletePostById(Long postId) throws IllegalArgumentException {
        // @TODO Add authentication logic.
        return postRepository.deletePostById(postId);
    }

    @InternalServerExceptionConverter
    public List<Post> getPostsByLocation(Location location) {
        return postRepository.selectPostByLocation(location, this.ALLOWED_POST_RANGE);
    }

    @InternalServerExceptionConverter
    public List<Post> getPostsByLocation(Location location, double allowRange) {
        return postRepository.selectPostByLocation(location, allowRange);
    }

}
