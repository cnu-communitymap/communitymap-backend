package com.swacademy.mapcommunity.domain.entity;

import com.swacademy.mapcommunity.domain.vo.Position;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post {
    private final UUID postId;
    private final UUID userId;
    private String title;
    private String content;
    private final LocalDateTime postDate;
    private int like;
    private final Position position;

    private static void validateTitle(String title) {
        if (title == null || title.length() == 0) throw new IllegalArgumentException("Title is not empty.");
    }

    public Post(UUID postId, UUID userId, String title, String content, LocalDateTime postDate, int like, Position position) {
        validateTitle(title);
        if (like < 0) throw new IllegalArgumentException("Like value cannot be negative.");
        if (postId == null) throw new IllegalArgumentException("post id cannot be null.");
        if (userId == null) throw new IllegalArgumentException("user id cannot be null.");
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.like = like;
        this.position = position;
    }

    public void changeTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void upLike() {
        this.like++;
    }

    public UUID getPostId() {
        return postId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public int getLike() {
        return like;
    }

    public Position getPosition() {
        return position;
    }
}
