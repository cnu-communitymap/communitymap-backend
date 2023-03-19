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
        return null;
    }

    @Override
    public Long updateUser(User updatedUser) throws IllegalArgumentException, InternalPersistenceException {
        return null;
    }

    @Override
    public boolean deleteUserById(Long userId) throws IllegalArgumentException, InternalPersistenceException {
        return false;
    }

    @Override
    public List<Post> selectPostsByUserId(Long userId) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<Comment> selectCommentsByUserId(Long userId) throws IllegalArgumentException {
        return null;
    }
}
