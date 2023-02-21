package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.vo.Gender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class associationMappingTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("User-Post 연관관계 test")
    void userPostTest() {
        User user = new User();
        user.setEmail("user_post@gmail.com");
        user.setGender(Gender.NONE);
        user.setPassword("user_post!!");
        user.setNickName("userPost");

        userRepository.save(user);

        Post post = new Post();
        post.setTitle("use and post");
        post.setContent("this is test post in PostRepositoryTest.java");

        post.setUser(user);    //연관관계 편의 메소드

        postRepository.save(post);
//        Post postEntity = entityManager.find(Post.class, user.getId());
//        log.info("postEntity: {}", postEntity.getUser().getNickName());
//        log.info("{}", post.getUser().getPosts().size());
    }

    @Test
    @DisplayName("Post-Comment 연관관계 test")
    void postCommentTest() {
        Post post = new Post();
        post.setTitle("post and comment test");
        post.setContent("this is post and comment association test");

        postRepository.save(post);

        Comment comment = new Comment();
        comment.setContent("post comment test");

        comment.setPost(post);

        commentRepository.save(comment);
    }

    @Test
    @DisplayName("User-Comment 연관관계 test")
    void userCommentTest() {
        User user = new User();
        user.setNickName("userComment");
        user.setPassword("userComment123!");
        user.setEmail("user_comment@gmail.com");
        user.setGender(Gender.NONE);

        userRepository.save(user);

        Comment comment = new Comment();
        comment.setContent("this is userComment association");
        comment.setUser(user);

        commentRepository.save(comment);
    }

    @Test
    @DisplayName("User-Post-Comment 연관관계")
    void userPostCommentTest() {
        User user = new User();
        user.setEmail("triangle@gmail.com");
        user.setPassword("triangle!");
        user.setNickName("triangle");
        user.setGender(Gender.NONE);

        userRepository.save(user);

        Post post = new Post();
        post.setContent("this is triangle test");
        post.setTitle("triangle test");

        post.setUser(user);

        postRepository.save(post);

        Comment comment = new Comment();
        comment.setContent("this is triangle test");

        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);
    }
}
