package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.aop.PersistenceExceptionConverter;
import com.swacademy.mapcommunity.data.entity.PostDataEntity;
import com.swacademy.mapcommunity.data.jpa.PostJpaRepository;
import com.swacademy.mapcommunity.data.mapper.PostMapper;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.entity.Location;
import com.swacademy.mapcommunity.domain.exception.InternalPersistenceException;
import com.swacademy.mapcommunity.domain.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PostRepositoryJpaImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;
    private final PostMapper postMapper;

    public PostRepositoryJpaImpl(PostJpaRepository postJpaRepository, PostMapper postMapper) {
        this.postJpaRepository = postJpaRepository;
        this.postMapper = postMapper;
    }


    @Override
    @PersistenceExceptionConverter
    public Long insertPost(Post post) throws IllegalArgumentException, InternalPersistenceException {
        PostDataEntity postDataEntity = this.postMapper.toDataEntity(post);
        return this.postJpaRepository.save(postDataEntity).getId();
    }

    @Override
    @PersistenceExceptionConverter
    public Post selectPostById(Long postId) throws IllegalArgumentException {
        PostDataEntity postDataEntity = this.postJpaRepository.getReferenceById(postId);
        return this.postMapper.toEntity(postDataEntity);
    }

    @Override
    @PersistenceExceptionConverter
    public Post selectPostById(Long postId, boolean getUser, boolean getComments) throws IllegalArgumentException {
        PostDataEntity postDataEntity = this.postJpaRepository.getReferenceById(postId);
        if (getUser) Hibernate.initialize(postDataEntity.getUser());
        if (getComments) Hibernate.initialize(postDataEntity.getComments());
        return this.postMapper.toEntity(postDataEntity);
    }

    @Override
    @PersistenceExceptionConverter
    public Long updatePost(Post updatedPost) throws IllegalArgumentException, InternalPersistenceException {
        PostDataEntity postDataEntity = this.postJpaRepository.getReferenceById(updatedPost.getId());
        postDataEntity.changePost(this.postMapper.toDataEntity(updatedPost));
        return postDataEntity.getId();
    }

    @Override
    @PersistenceExceptionConverter
    public void deletePostById(Long postId) throws IllegalArgumentException, InternalPersistenceException {
        PostDataEntity postDataEntity = this.postJpaRepository.getReferenceById(postId);
        this.postJpaRepository.delete(postDataEntity);
    }

    @Override
    @PersistenceExceptionConverter
    public List<Comment> selectCommentsByPostId(Long postId) throws IllegalArgumentException {
        PostDataEntity postDataEntity = this.postJpaRepository.getReferenceById(postId);
        Hibernate.initialize(postDataEntity.getComments());
        return this.postMapper.toEntity(postDataEntity).getComments();
    }

    @Override
    @PersistenceExceptionConverter
    public User selectUserByPostId(Long postId) throws IllegalArgumentException {
        PostDataEntity postDataEntity = this.postJpaRepository.getReferenceById(postId);
        Hibernate.initialize(postDataEntity.getUser());
        return this.postMapper.toEntity(postDataEntity).getUser();
    }

    @Override
    @PersistenceExceptionConverter
    public List<Post> selectPostByLocation(Location location, double allowRange) throws IllegalArgumentException {
        // @TODO Implement this.
        return null;
    }
}
