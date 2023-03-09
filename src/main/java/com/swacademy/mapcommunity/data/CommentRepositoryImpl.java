package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.data.repository.CommentJpaRepository;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.exception.InternalPersistenceException;
import com.swacademy.mapcommunity.domain.repository.CommentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    public CommentRepositoryImpl(CommentJpaRepository commentJpaRepository) {
        this.commentJpaRepository = commentJpaRepository;
    }


    @Override
    public Long insertComment(Comment comment) throws IllegalArgumentException, InternalPersistenceException {
        return null;
    }

    @Override
    public Comment selectCommentById(Long commentId) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Comment selectCommentById(Long commentId, boolean getUser, boolean getPost) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Long updateComment(Comment updatedComment) throws IllegalArgumentException, InternalPersistenceException {
        return null;
    }

    @Override
    public boolean deleteCommentById(Long commentId) throws IllegalArgumentException, InternalPersistenceException {
        return false;
    }

    @Override
    public User selectUserByCommentId(Long commentId) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Post selectPostByCommentId(Long commentId) throws IllegalArgumentException {
        return null;
    }
}
