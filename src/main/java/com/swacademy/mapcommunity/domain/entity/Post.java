package com.swacademy.mapcommunity.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
public class Post extends BaseInformation {
    private Long id;
    private String title;
    private String content;
    private Integer postLike;
    private Location position;
//    private Long userId;
    private User user;
    private List<Comment> comments = new ArrayList<>();

    public Post() { super(); }

    public Post(Long id, String title, String content, Integer postLike, Location position,
                User user, List<Comment> comments,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.title = title;
        this.content = content;
        this.postLike = postLike;
        this.position = position;
        this.user = user;
        this.comments = comments;
    }

    public void setUser(User user) {
        if (Objects.nonNull(this.user)) this.user.getPosts().remove(this);
        this.user = user;
        this.user.getPosts().add(this);
    }

    /**
     * Remove the User object associated with the User field of the Post object,
     * and remove the corresponding Post object from the Post List of the User object.
     * @param user User
     */
    public void unsetUser(User user) {
        if (Objects.nonNull(this.user) && this.user.equals(user)) {
            this.user = null;
            user.getPosts().remove(this);
        }
    }

    public void addComment(Comment comment) {
        comment.setPost(this);
    }

    public void removeComment(Comment comment) {
        comment.unsetPost(this);
    }

}
