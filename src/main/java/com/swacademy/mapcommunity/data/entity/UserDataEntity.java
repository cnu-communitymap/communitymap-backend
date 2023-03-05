package com.swacademy.mapcommunity.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
public class UserDataEntity extends BaseInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, length = 30, unique = true)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "birth", columnDefinition = "DATE")
    private LocalDate birth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostDataEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentDataEntity> comments = new ArrayList<>();

    public void addPost(PostDataEntity post) {
        post.setUser(this);
    }

    public void addComment(CommentDataEntity comment) {
        comment.setUser(this);
    }

    @PrePersist
    private void prePersist() {
        this.gender = this.gender == null ? Gender.NONE : this.gender;
    }

}
