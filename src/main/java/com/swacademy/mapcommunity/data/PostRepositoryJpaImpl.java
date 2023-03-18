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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryJpaImpl implements PostRepository {

    private final PostJpaRepository postRepository;
    private final PostMapper postMapper;

    public PostRepositoryJpaImpl(PostJpaRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Override
    @Transactional
    @PersistenceExceptionConverter
    public Long insertPost(Post post) throws IllegalArgumentException, InternalPersistenceException {
        PostDataEntity postDataEntity = this.postMapper.toDataEntity(post);
        return this.postRepository.save(postDataEntity).getId();
    }

    @Override
    @Transactional
    @PersistenceExceptionConverter
    public Post selectPostById(Long postId) {
        PostDataEntity postDataEntity = this.postRepository.getReferenceById(postId);
        return this.postMapper.toEntity(postDataEntity);
    }

    @Override
    public Post selectPostById(Long postId, boolean getUser, boolean getComments) {
        return null;
    }

    @Override
    public Long updatePost(Post updatedPost) {
        return null;
    }

    @Override
    public boolean deletePostById(Long postId) {
        return false;
    }

    @Override
    public List<Comment> selectCommentsByPostId(Long postId) {
        return null;
    }

    @Override
    public User selectUserByPostId(Long postId) {
        return null;
    }

    @Override
    public List<Post> selectPostByLocation(Location location, double allowRange) {
        return null;
    }
}
