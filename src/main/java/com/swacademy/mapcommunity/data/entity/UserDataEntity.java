package com.swacademy.mapcommunity.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostDataEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentDataEntity> comments = new ArrayList<>();

    public void changeUser(UserDataEntity userDataEntity) {
        this.email = userDataEntity.getEmail();
        this.password = userDataEntity.getPassword();
        this.nickname = userDataEntity.getNickname();
        this.gender = userDataEntity.getGender();
        this.birth = userDataEntity.getBirth();
    }

    public void addPost(PostDataEntity post) {
        post.setUser(this);
    }

    /**
     * When a Post is deleted, all Comments that the Post has must be deleted.
     * @param post the post to be removed
     */
    public void removePost(PostDataEntity post) {
        post.unsetUser(this);
        post.getComments().removeAll(comments);
    }

    public void addComment(CommentDataEntity comment) {
        comment.setUser(this);
    }

    /**
     * removes a comment from both the user and post that it is associated with.
     * The comment is first unset from the user's comments list, then it is unset from the post's comments list.
     * @param comment the comment to be removed
     */
    public void removeComment(CommentDataEntity comment) {
        comment.unsetUser(this);
        comment.unsetPost(comment.getPost());
    }

    @PrePersist
    private void prePersist() {
        this.gender = this.gender == null ? Gender.NONE : this.gender;
    }

}
