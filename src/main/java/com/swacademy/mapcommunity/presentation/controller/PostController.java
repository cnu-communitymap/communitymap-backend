package com.swacademy.mapcommunity.presentation.controller;

import com.swacademy.mapcommunity.domain.entity.Category;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.service.PostService;
import com.swacademy.mapcommunity.domain.service.UserService;
import com.swacademy.mapcommunity.presentation.dto.CommentDto;
import com.swacademy.mapcommunity.presentation.dto.PostDto;
import com.swacademy.mapcommunity.presentation.mapper.CommentMapper;
import com.swacademy.mapcommunity.presentation.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("post")
public class PostController {

    private final PostMapper postMapper;
    private final PostService postService;
    private final UserService userService;
    private final CommentMapper commentMapper;

    @Autowired
    public PostController(PostMapper postMapper, PostService postService, UserService userService, CommentMapper commentMapper) {
        this.postMapper = postMapper;
        this.postService = postService;
        this.userService = userService;
        this.commentMapper = commentMapper;
    }

    //@Todo Long -> UUID
    @PostMapping(value = "/write",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> create(
            @RequestPart(value = "post") PostDto postDto,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        Post entity = postMapper.toEntity(postDto);

        postService.savePost(postMapper.toEntity(postDto), userService.getLoggedInUser(), file);

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
        PostDto postDto = postMapper.toDto(entity);
        String imageUrl = postService.getImageUrl(entity);
        postDto.setImageUrl(imageUrl);
        return postDto;
    }

    @GetMapping(value = "/comments")
    public List<CommentDto> readComments(@RequestParam Long postId) {
        List<Comment> comments = postService.getCommentsByPostId(postId);
        return comments.stream().map(commentMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/category")
    public List<PostDto> readByCategory(@RequestParam("category")Category category) {
        List<Post> posts = postService.getPostsByCategory(category);
        return posts.stream().map(postMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/delete")
    public void delete(@RequestParam("postId") Long postId) {
        postService.deletePostById(postId);
    }
}
