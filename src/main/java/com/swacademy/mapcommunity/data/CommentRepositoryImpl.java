package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.repository.CommentRepository;

import java.util.Optional;
import java.util.UUID;

public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public Comment insert(Comment comment) {
        return null;
    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public void deleteById(UUID commentId) {

    }

    @Override
    public Optional<Comment> getCommentById(UUID commentId) {
        return Optional.empty();
    }

    @Override
    public boolean upLike(UUID commentId) {
        return false;
    }
}
