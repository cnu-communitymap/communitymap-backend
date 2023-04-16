package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.aop.InternalServerExceptionConverter;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.exception.InternalServerException;
import com.swacademy.mapcommunity.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final Environment environment;

    private final int ALLOWED_POST_RANGE = 100;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService, Environment environment) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.environment = environment;
    }

    @InternalServerExceptionConverter
    public Long savePost(Post post, User writer, MultipartFile file) throws IllegalArgumentException, InternalServerException, IOException {
        // Get the external upload path
        if (file != null){
            String uploadPath = environment.getProperty("upload.path");
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename(); //Create UUID and append it to FileName
            File saveFile = new File(uploadPath, filename);    //Save the uploaded file to the actual path
            file.transferTo(saveFile);      //Using the transferTo() method, move the uploaded file to the corresponding file object.
            post.setFileName(filename);
            post.setFilePath(uploadPath);
        }
        post.setUser(writer);
        return postRepository.insertPost(post);
    }

    /**
     * @param post Post
     * @return : The URL to be provided to the client.
     */
    @InternalServerExceptionConverter
    public String getImageUrl(Post post) {

        if(post.getFileName() == null){   //no img
            return null;
        }
        else {
            return "http://localhost:8080"+"/images/" + post.getFileName();      //@Todo Change server ip
        }
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
