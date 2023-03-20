package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.data.entity.CommentDataEntity;
import com.swacademy.mapcommunity.data.mapper.CommentMapper;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.data.jpa.CommentJpaRepository;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.exception.InternalPersistenceException;
import com.swacademy.mapcommunity.domain.repository.CommentRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;
    private final CommentMapper commentMapper;

    public CommentRepositoryJpaImpl(CommentJpaRepository commentJpaRepository, CommentMapper commentMapper) {
        this.commentJpaRepository = commentJpaRepository;
        this.commentMapper = commentMapper;
    }


    @Override
    public Long insertComment(Comment comment) throws IllegalArgumentException, InternalPersistenceException {
        CommentDataEntity commentDataEntity = this.commentMapper.toDataEntity(comment);
        return this.commentJpaRepository.save(commentDataEntity).getId();
    }

    @Override
    public Comment selectCommentById(Long commentId) throws IllegalArgumentException {
        CommentDataEntity commentDataEntity = this.commentJpaRepository.getReferenceById(commentId);
        return this.commentMapper.toEntity(commentDataEntity);
    }

    @Override
    public Comment selectCommentById(Long commentId, boolean getUser, boolean getPost) throws IllegalArgumentException {
        CommentDataEntity commentDataEntity = this.commentJpaRepository.getReferenceById(commentId);
        if (getUser) Hibernate.initialize(commentDataEntity.getUser());
        if (getPost) Hibernate.initialize(commentDataEntity.getPost());
        return this.commentMapper.toEntity(commentDataEntity);
    }

    @Override
    public Long updateComment(Comment updatedComment) throws IllegalArgumentException, InternalPersistenceException {
        CommentDataEntity commentDataEntity = this.commentJpaRepository.getReferenceById(updatedComment.getId());
        commentDataEntity.changeComment(this.commentMapper.toDataEntity(updatedComment));
        return commentDataEntity.getId();
    }

    @Override
    public void deleteCommentById(Long commentId) throws IllegalArgumentException, InternalPersistenceException {
        CommentDataEntity commentDataEntity = this.commentJpaRepository.getReferenceById(commentId);
        this.commentJpaRepository.delete(commentDataEntity);
    }

    @Override
    public User selectUserByCommentId(Long commentId) throws IllegalArgumentException {
        CommentDataEntity commentDataEntity = this.commentJpaRepository.getReferenceById(commentId);
        Hibernate.initialize(commentDataEntity.getUser());
        return this.commentMapper.toEntity(commentDataEntity).getUser();
    }

    @Override
    public Post selectPostByCommentId(Long commentId) throws IllegalArgumentException {
        CommentDataEntity commentDataEntity = this.commentJpaRepository.getReferenceById(commentId);
        Hibernate.initialize(commentDataEntity.getPost());
        return this.commentMapper.toEntity(commentDataEntity).getPost();
    }
}
