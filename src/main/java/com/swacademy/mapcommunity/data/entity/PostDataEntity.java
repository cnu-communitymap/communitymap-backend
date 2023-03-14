package com.swacademy.mapcommunity.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
@Getter @Setter
public class PostDataEntity extends BaseInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    @Column(name = "post_like", nullable = false)
    @ColumnDefault("0")
    private Integer postLike;

    @Column(name = "position", nullable = false, columnDefinition = "GEOMETRY")
    private Point position;

    //필요한가 싶어서 일단 제거
//    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
//    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDataEntity user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentDataEntity> comments = new ArrayList<>();

    public void setUser(UserDataEntity user) {
        if (Objects.nonNull(this.user)) this.user.getPosts().remove(this);
        this.user = user;
        user.getPosts().add(this);
    }

    /**
     * Remove the User object associated with the User field of the Post object,
     * and remove the corresponding Post object from the Post List of the User object.
     * @param user UserDataEntity
     */
    public void unsetUser(UserDataEntity user) {
        if (Objects.nonNull(this.user) && this.user.equals(user)) {
            this.user = null;
            user.getPosts().remove(this);
        }
    }

    public void addComment(CommentDataEntity comment) {
        comment.setPost(this);
    }

    public void removeComment(CommentDataEntity comment) {
        comment.unsetPost(this);
    }

    @PrePersist
    private void prePersist() {
        this.postLike = this.postLike == null ? 0 : this.postLike;
    }
}
