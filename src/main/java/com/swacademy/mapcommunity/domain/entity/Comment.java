package com.swacademy.mapcommunity.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comment")
@Getter
@Setter
@DynamicInsert
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "comment_like")
    @ColumnDefault("0")
    private int commentLike;

    @CreationTimestamp
    @Column(name = "comment_date_time")
    private LocalDateTime commentDatetime;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void setPost(Post post) {
        if (Objects.nonNull(this.post)) {
            this.post.getComments().remove(this);
        }
        this.post = post;
        post.getComments().add(this);
    }


    public void setUser(User user) {
        if (Objects.nonNull(this.user)) {
            this.user.getComments().remove(this);
        }
        this.user = user;
        user.getComments().add(this);
    }
}
