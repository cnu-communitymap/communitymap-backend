package com.swacademy.mapcommunity.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
public class Comment extends BaseInformation {
    private Long id;
    private String content;
    private Integer commentLike;
//    private Long userId;
    private User user;
    private Post post;

    public Comment() { super(); }

    public Comment(Long id, String content, Integer commentLike, User user,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.content = content;
        this.commentLike = commentLike;
        this.user = user;
    }

    public void setUser(User user) {
        if (Objects.nonNull(this.user)) this.user.getComments().remove(this);
        this.user = user;
        this.user.getComments().add(this);
    }

    public void unsetUser(User user) {
        if(Objects.nonNull(this.user) && this.user.equals(user)) {
            this.user = null;
            user.getComments().remove(this);
        }
    }

    public void setPost(Post post) {
        if (Objects.nonNull(this.post)) this.post.getComments().remove(this);
        this.post = post;
        this.post.getComments().add(this);
    }

    public void unsetPost(Post post) {
        if(Objects.nonNull(this.post) && this.post.equals(post)) {
            this.post = null;
            post.getComments().remove(this);
        }
    }
}
