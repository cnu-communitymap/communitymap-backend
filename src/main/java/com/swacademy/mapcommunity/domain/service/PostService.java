package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.aop.InternalServerExceptionConverter;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.exception.InternalServerException;
import com.swacademy.mapcommunity.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    private final int ALLOWED_POST_RANGE = 100;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @InternalServerExceptionConverter
    public Long savePost(Post post, User writer) throws IllegalArgumentException, InternalServerException {
        post.setUser(writer);  // @TODO Change this code that using spring security.
        return postRepository.insertPost(post);
    }

    @InternalServerExceptionConverter
    public Post getPostById(Long postId) throws IllegalArgumentException {
        return postRepository.selectPostById(postId);
    }

    @InternalServerExceptionConverter
    public Post getPostWithUserAndCommentById(Long postId) throws IllegalArgumentException {
        return postRepository.selectPostById(postId, true, true);
    }

    @InternalServerExceptionConverter
    public User getWriterByPostId(Long postId) throws IllegalArgumentException {
        return postRepository.selectUserByPostId(postId);
    }

    @InternalServerExceptionConverter
    public List<Comment> getCommentsByPostId(Long postId) throws IllegalArgumentException {
        return postRepository.selectCommentsByPostId(postId);
    }

    @InternalServerExceptionConverter
    public Long updatePost(Post updatedPost) throws IllegalArgumentException {
        // @TODO Add authentication logic.
        return postRepository.updatePost(updatedPost);
    }

    @InternalServerExceptionConverter
    public void deletePostById(Long postId) throws IllegalArgumentException {
        // @TODO Add authentication logic.
        User loggedUser = userService.getLoggedInUser();
        if (loggedUser == null ||
                ! getPostById(postId).getUser().getId().equals(loggedUser.getId()))
        {
            throw new IllegalArgumentException("not the user who wrote the post");
        }
        postRepository.deletePostById(postId);
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
