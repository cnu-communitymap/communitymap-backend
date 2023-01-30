package com.swacademy.mapcommunity.domain.vo;

import java.util.Date;

public record PostContent(
        String title,
        String content,
        Date postDate,
        int like
) {
    public PostContent {
        // @TODO title, content, postData 제약 생각해보기.
        if (like < 0) throw new IllegalArgumentException("Like value cannot be negative.");
    }
}
