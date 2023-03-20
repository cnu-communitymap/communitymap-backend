package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.data.entity.UserDataEntity;
import com.swacademy.mapcommunity.data.jpa.UserJpaRepository;
import com.swacademy.mapcommunity.data.mapper.UserMapper;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.exception.InternalPersistenceException;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class UserRepositoryJpaImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public UserRepositoryJpaImpl(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }


    @Override
    public Long insertUser(User user) throws IllegalArgumentException, InternalPersistenceException {
        UserDataEntity userDataEntity = this.userMapper.toDataEntity(user);
        return this.userJpaRepository.save(userDataEntity).getId();
    }

    @Override
    public User selectUserById(Long userId) throws IllegalArgumentException {
        UserDataEntity userDataEntity = this.userJpaRepository.getReferenceById(userId);
        return this.userMapper.toEntity(userDataEntity);
    }

    @Override
    public User selectUserById(Long userId, boolean getPosts, boolean getComments) throws IllegalArgumentException {
        UserDataEntity userDataEntity = this.userJpaRepository.getReferenceById(userId);
        if (getPosts) Hibernate.initialize(userDataEntity.getPosts());
        if (getComments) Hibernate.initialize(userDataEntity.getComments());
        return this.userMapper.toEntity(userDataEntity);
    }

    @Override
    public Long updateUser(User updatedUser) throws IllegalArgumentException, InternalPersistenceException {
        UserDataEntity userDataEntity = this.userJpaRepository.getReferenceById(updatedUser.getId());
        userDataEntity.changeUser(this.userMapper.toDataEntity(updatedUser));
        return userDataEntity.getId();
    }

    @Override
    public void deleteUserById(Long userId) throws IllegalArgumentException, InternalPersistenceException {
        UserDataEntity userDataEntity = this.userJpaRepository.getReferenceById(userId);
        this.userJpaRepository.delete(userDataEntity);
    }

    @Override
    public List<Post> selectPostsByUserId(Long userId) throws IllegalArgumentException {
        UserDataEntity userDataEntity = this.userJpaRepository.getReferenceById(userId);
        Hibernate.initialize(userDataEntity.getPosts());
        return this.userMapper.toEntity(userDataEntity).getPosts();
    }

    @Override
    public List<Comment> selectCommentsByUserId(Long userId) throws IllegalArgumentException {
        UserDataEntity userDataEntity = this.userJpaRepository.getReferenceById(userId);
        Hibernate.initialize(userDataEntity.getComments());
        return this.userMapper.toEntity(userDataEntity).getComments();
    }
}
