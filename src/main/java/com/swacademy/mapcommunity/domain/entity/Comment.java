package com.swacademy.mapcommunity.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter @Setter
public class Comment extends BaseInformation {
    private Long id;
    private String content;
    private Integer commentLike;
    private Long postedId;
    @JsonBackReference
    private User user;
    @JsonBackReference
    private Post post;  //parent

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
        //this.user.getComments().add(this);
    }

    public void unsetUser(User user) {
        if(Objects.nonNull(this.user) && this.user.equals(user)) {
            this.user = null;
            user.getComments().remove(this);
        }
    }

    public void setPost(Post post) {
        if (Objects.nonNull(this.post)) {
            this.post.getComments().remove(this);
        }
        this.post = post;
        if (Objects.nonNull(post)) {
            post.getComments().add(this);
        }
        this.setPostedId(post.getId());
    }

    public void unsetPost(Post post) {
        if (Objects.nonNull(this.post) && this.post.equals(post)) {
            this.post = null;
            post.getComments().remove(this);
        }
    }





}
