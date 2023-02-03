package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.Comment;

import java.util.UUID;

public interface CommentRepository {

    Comment registerComment(Comment comment);

    // @TODO UUID 생각
    Comment getComment(UUID commentId);

    boolean upLike(UUID commentId);

}
