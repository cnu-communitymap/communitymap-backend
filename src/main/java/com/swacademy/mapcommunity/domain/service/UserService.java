package com.swacademy.mapcommunity.domain.service;

import com.swacademy.mapcommunity.domain.entity.Post;
import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.entity.Comment;
import com.swacademy.mapcommunity.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long saveUser(User user) throws IOException {
        return userRepository.insertUser(user);
    }

    public User getUserById(Long userId) {
        return userRepository.selectUserById(userId);
    }

    public User getUserWithPostById(Long userId) {
        return userRepository.selectUserById(userId, true, false);
    }

    public User getUserWithCommentById(Long userId) {
        return userRepository.selectUserById(userId, false, true);
    }

    public User getUserWithPostAndCommentById(Long userId) {
        return userRepository.selectUserById(userId, true, true);
    }

    public List<Post> getUserPostsByUserId(Long userId) {
        return userRepository.selectPostsByUserId(userId);
    }

    public List<Comment> getUserCommentsByUserId(Long userId) {
        return userRepository.selectCommentsByUserId(userId);
    }

    public Long updateUser(User updatedUser) throws IOException {
        return userRepository.updateUser(updatedUser);
    }
    
    public boolean deleteUserById(Long userId) throws IOException {
        return userRepository.deleteUserById(userId);
    }

}
