package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.Comment;

import java.util.UUID;

public interface CommentRepository {

    Comment registerComment(Comment comment);

    Comment getComment(UUID commentId);

    boolean upLike(UUID commentId);
}
