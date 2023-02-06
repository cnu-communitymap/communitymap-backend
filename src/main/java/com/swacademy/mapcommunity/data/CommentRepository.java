package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.Comment;

import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {

    Comment insert(Comment comment);

    // @ToDo update 제약 상속받는 클래스에 추가 . ex)아무 것도 입력 X 경우
    Comment update(Comment comment);

    void deleteById(UUID commentId);

    /**
     *
     * @param commentId UUID
     * @return Comment. null일 수도 있다.
     */
    Optional<Comment> getCommentById(UUID commentId);

    boolean upLike(UUID commentId);

}
