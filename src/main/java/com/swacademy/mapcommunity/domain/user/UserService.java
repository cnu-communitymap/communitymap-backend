package com.swacademy.mapcommunity.domain.user;

import com.swacademy.mapcommunity.domain.repository.UserRepository;
import com.swacademy.mapcommunity.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // @Todo 레포지토리 함수 try-catch 감싸야 함?
    /**
     * @param userId UUID
     * @return If userId is invalid id, return null. else return User object
     */
    User getUser(UUID userId) {
        var user = userRepository.getUserById(userId);
        if (user.isEmpty()) return null;
        else return user.get();
    }

    User getUser(String email) {
        var user = userRepository.getUserByEmail(email);
        if (user.isEmpty()) return null;
        else return user.get();
    }

    // @Todo 여기서 Exception 발생시키는 게 맞나?
    public User register(User user) {
        if (validateDuplicateUserEmail(user)) userRepository.insertUser(user);
        else throw new RuntimeException("Duplicated user email.");
        return user;
    }

    // @TODO ADD Authorization logic
    public User updateUser(User user) {
        try {
            userRepository.updateUser(user);
        } catch (RuntimeException e) {
            return null;
        }
        return user;
    }

    // @TODO ADD Authorization logic
    public boolean withdrawal(User user) {
        try {
            userRepository.deleteUser(user);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }

    private boolean validateDuplicateUserEmail(User user){
        User findUser = this.getUser(user.getUserId());
        return findUser != null;
    }

}