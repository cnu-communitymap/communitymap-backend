package com.swacademy.mapcommunity.domain.entity;

import com.swacademy.mapcommunity.domain.vo.CommentContent;
import com.swacademy.mapcommunity.domain.vo.UserInfo;

import java.util.UUID;

public class Comment {
    private final UUID commentId;
    private final UUID registeredPostId;
    // @TODO UserInfo를 Entity인 User로 바꿔도 되는가? -> UUID 빼오는 거 때문에.
    private final UserInfo author;
    private final CommentContent commentContent;

    public Comment(UUID commentId, UUID registeredPostId, UserInfo userInfo, CommentContent commentContent) {
        this.commentId = commentId;
        this.registeredPostId = registeredPostId;
        this.author = userInfo;
        this.commentContent = commentContent;
    }
}