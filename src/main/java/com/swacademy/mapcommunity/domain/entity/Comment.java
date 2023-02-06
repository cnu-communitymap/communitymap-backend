package com.swacademy.mapcommunity.domain.entity;

import java.util.Date;
import java.util.UUID;

public class Comment {
    private final UUID commentId;
    private final UUID registeredPostId;
    private final UUID userId;
    private String commentContent;
    private final Date commentDate;
    private int like;

    /**
     *
     * @param commentId UUID
     * @param registeredPostId UUID
     * @param userId UUID
     * @param commentContent String, not null
     * @param commentDate Date
     * @param like int, not negative -> repository에서 예외처리
     */
    public Comment(UUID commentId, UUID registeredPostId, UUID userId, String commentContent, Date commentDate, Integer like) {
        validateContent(commentContent);
        this.commentId = commentId;
        this.registeredPostId = registeredPostId;
        this.userId = userId;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.like = like;
    }

    /**
     *
     * @param commentContent String, 공백만 있거나 null인지 판단
     */
    private void validateContent(String commentContent) {
        if(commentContent.isEmpty()){
            throw new RuntimeException("commentContent should not be blank");
        }
    }

    public void changeCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void upLike(int like) {
        this.like++;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public UUID getRegisteredPostId() {
        return registeredPostId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public int getLike() {
        return like;
    }
}
