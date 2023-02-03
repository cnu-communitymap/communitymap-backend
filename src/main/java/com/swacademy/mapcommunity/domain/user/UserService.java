package com.swacademy.mapcommunity.domain.user;

import com.swacademy.mapcommunity.domain.repository.UserRepository;
import com.swacademy.mapcommunity.domain.entity.User;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param userId UUID
     * @return If userId is invalid id, return null. Else return User object.
     */
    @Nullable
    User getUser(UUID userId) {
        try {
            var user = userRepository.getUserById(userId);
            if (user.isEmpty()) return null;
            else return user.get();
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * @param email String
     * @return If giving email is invalid email, return null. Else return User object.
     */
    @Nullable
    User getUser(String email) {
        try {
            var user = userRepository.getUserByEmail(email);
            if (user.isEmpty()) return null;
            else return user.get();
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * @param user User
     * @return If register process failed, return null. Else return User object.
     */
    @Nullable
    public User register(User user) {
        try {
            if (validateDuplicateUserEmail(user)) userRepository.insertUser(user);
            else throw new RuntimeException("Duplicated user email.");
            return user;
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * @param user User
     * @return If update process failed, return null. Else return updated User object.
     */
    @Nullable
    public User updateUser(User user) {
        // @TODO ADD Authorization logic
        try {
            userRepository.updateUser(user);
        } catch (RuntimeException e) {
            return null;
        }
        return user;
    }

    /**
     * @param user User
     * @return Deletion is succeeded or not: boolean
     */
    public boolean withdrawal(User user) {
        // @TODO ADD Authorization logic
        try {
            userRepository.deleteUser(user);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * @param user User
     * @return If there is no email duplicate, return true. Else return false.
     */
    private boolean validateDuplicateUserEmail(User user){
        User findUser = this.getUser(user.getEmail());
        return findUser == null;
    }

}
