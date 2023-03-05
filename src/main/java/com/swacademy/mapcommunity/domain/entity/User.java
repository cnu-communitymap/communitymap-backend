package com.swacademy.mapcommunity.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class User extends BaseInformation {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Gender gender;
    private LocalDate birth;
    private List<Post> posts = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public User() { super(); }

    public User(Long id, String email, String password, String nickname, Gender gender, LocalDate birth,
                List<Post> posts, List<Comment> comments,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = birth;
        this.posts = posts;
        this.comments = comments;
    }

    public void addPost(Post post) {
        post.setUser(this);
    }

    public void addComment(Comment comment) {
        comment.setUser(this);
    }

}
