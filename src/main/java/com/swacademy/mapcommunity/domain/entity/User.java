package com.swacademy.mapcommunity.domain.entity;

import com.swacademy.mapcommunity.domain.vo.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nick_name", nullable = false, length = 30, unique = true)
    private String nickName;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    //fk
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


    /**
     * 연관관계 편의 메소드
     */
    public void addPost(Post post) {
        post.setUser(this);
    }

    public void addComment(Comment comment) {
        comment.setUser(this);
    }
}
