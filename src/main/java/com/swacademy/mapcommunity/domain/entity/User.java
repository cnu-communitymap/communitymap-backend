package com.swacademy.mapcommunity.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Post> posts = new ArrayList<>();
    @JsonIgnoreProperties(value = {"user"}, allowSetters = true)
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

    /**
     * When a Post is deleted, all Comments that the Post has must be deleted.
     * @param post Post the post to be removed
     */
    public void removePost(Post post) {
        post.unsetUser(this);
        post.getComments().removeAll(comments);
    }

    public void addComment(Comment comment) {
        comment.setUser(this);
    }

    /**
     * removes a comment from both the user and post that it is associated with.
     * The comment is first unset from the user's comments list, then it is unset from the post's comments list.
     * @param comment the comment to be removed
     */
    public void removeComment(Comment comment) {
        comment.unsetUser(this);
        comment.unsetPost(comment.getPost());
    }

}