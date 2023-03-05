package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.data.repository.CommentJpaRepository;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.repository.CommentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    public CommentRepositoryImpl(CommentJpaRepository commentJpaRepository) {
        this.commentJpaRepository = commentJpaRepository;
    }


    @Override
    public Long insertComment(Comment comment) {
        return null;
    }

    @Override
    public Comment selectCommentById(Long commentId) {
        return null;
    }

    @Override
    public Comment selectCommentById(Long commentId, boolean getUser) {
        return null;
    }

    @Override
    public Long updateComment(Comment updatedComment) {
        return null;
    }

    @Override
    public boolean deleteCommentById(Long commentId) {
        return false;
    }

    @Override
    public User selectUserByCommentId(Long commentId) {
        return null;
    }
}
