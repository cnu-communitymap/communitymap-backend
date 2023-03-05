package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.data.repository.UserJpaRepository;
import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    public Long insertUser(User user) {
        return null;
    }

    @Override
    public User selectUserById(Long userId) {
        return null;
    }

    @Override
    public User selectUserById(Long userId, boolean getPosts, boolean getComments) {
        return null;
    }

    @Override
    public Long updateUser(User updatedUser) {
        return null;
    }

    @Override
    public boolean deleteUserById(Long userId) {
        return false;
    }

    @Override
    public List<Post> selectPostsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Comment> selectCommentsByUserId(Long userId) {
        return null;
    }
}
