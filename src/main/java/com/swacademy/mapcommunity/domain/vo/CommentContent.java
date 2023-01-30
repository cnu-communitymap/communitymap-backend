package com.swacademy.mapcommunity.domain.vo;

import java.util.Date;

public record CommentContent(
        String content,
        Date commentDate,
        int like
) {
    public CommentContent {
        // @TODO title, content, postData 제약 생각해보기.
        if (like < 0) throw new IllegalArgumentException("Like value cannot be negative.");
    }
}
