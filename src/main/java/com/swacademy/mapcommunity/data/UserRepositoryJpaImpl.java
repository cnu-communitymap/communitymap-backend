package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.data.jpa.UserJpaRepository;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.exception.InternalPersistenceException;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryJpaImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryJpaImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    public Long insertUser(User user) throws IllegalArgumentException, InternalPersistenceException {
        return null;
    }

    @Override
    public User selectUserById(Long userId) throws IllegalArgumentException {
        return null;
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
