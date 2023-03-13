package com.swacademy.mapcommunity.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Entity
@Table(name = "comments")
@Getter @Setter
public class CommentDataEntity extends BaseInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    @Column(name = "comment_like", nullable = false)
    @ColumnDefault("0")
    private Integer commentLike;

//    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
//    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDataEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private PostDataEntity post;

    public void setUser(UserDataEntity user) {
        if (Objects.nonNull(this.user)) this.user.getComments().remove(this);
        this.user = user;
        user.getComments().add(this);
    }

    public void setPost(PostDataEntity post) {
        if (Objects.nonNull(this.post)) this.post.getComments().remove(this);
        this.post = post;
        post.getComments().add(this);
    }

    @PrePersist
    public void prePersist() {
        this.commentLike = this.commentLike == null ? 0 : this.commentLike;
    }

}
