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
    private Long userId;
    private User user;

    public Comment() { super(); }

    public Comment(Long id, String content, Integer commentLike, Long userId, User user,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.content = content;
        this.commentLike = commentLike;
        this.userId = userId;
        this.user = user;
    }

    public void setUser(User user) {
        if (Objects.nonNull(this.user)) this.user.getComments().remove(this);
        this.user = user;
        this.user.getComments().add(this);
    }

}
