package com.swacademy.mapcommunity.domain.entity;

import com.swacademy.mapcommunity.domain.vo.Position;
import com.swacademy.mapcommunity.domain.vo.PostContent;
import com.swacademy.mapcommunity.domain.vo.UserInfo;

import java.util.UUID;

public class Post {
    private final UUID postId;
    // @TODO UserInfo를 Entity인 User로 바꿔도 되는가? -> UUID 빼오는 거 때문에.
    private final UserInfo author;
    private final PostContent postContent;
    private final Position position;

    public Post(UUID postId, UserInfo author, PostContent postContent, Position position) {
        this.postId = postId;
        this.author = author;
        this.postContent = postContent;
        this.position = position;
    }
}
