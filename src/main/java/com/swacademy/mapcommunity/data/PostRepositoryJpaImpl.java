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

    private final PostJpaRepository postRepository;
    private final PostMapper postMapper;

    public PostRepositoryJpaImpl(PostJpaRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Override
    @PersistenceExceptionConverter
    public Long insertPost(Post post) throws IllegalArgumentException, InternalPersistenceException {
        PostDataEntity postDataEntity = this.postMapper.toDataEntity(post);
        return this.postRepository.save(postDataEntity).getId();
    }

    @Override
    @PersistenceExceptionConverter
    public Post selectPostById(Long postId) throws IllegalArgumentException {
        PostDataEntity postDataEntity = this.postRepository.getReferenceById(postId);
        return this.postMapper.toEntity(postDataEntity);
    }

    @Override
    @PersistenceExceptionConverter
    public Post selectPostById(Long postId, boolean getUser, boolean getComments) throws IllegalArgumentException {
        PostDataEntity postDataEntity = this.postRepository.getReferenceById(postId);
        if (getUser) Hibernate.initialize(postDataEntity.getUser());
        if (getComments) Hibernate.initialize(postDataEntity.getComments());
        return null;
    }

    @Override
    @PersistenceExceptionConverter
    public Long updatePost(Post updatedPost) throws IllegalArgumentException, InternalPersistenceException {
        PostDataEntity postDataEntity = this.postRepository.getReferenceById(updatedPost.getId());
        postDataEntity.changePost(this.postMapper.toDataEntity(updatedPost));
        return postDataEntity.getId();
    }

    @Override
    @PersistenceExceptionConverter
    public boolean deletePostById(Long postId) throws IllegalArgumentException, InternalPersistenceException {
        return false;
    }

    @Override
    @PersistenceExceptionConverter
    public List<Comment> selectCommentsByPostId(Long postId) throws IllegalArgumentException {
        return null;
    }

    @Override
    @PersistenceExceptionConverter
    public User selectUserByPostId(Long postId) throws IllegalArgumentException {
        return null;
    }

    @Override
    @PersistenceExceptionConverter
    public List<Post> selectPostByLocation(Location location, double allowRange) throws IllegalArgumentException {
        return null;
    }
}
