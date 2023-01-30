package com.swacademy.mapcommunity.domain.user;

import com.swacademy.mapcommunity.data.UserRepository;
import com.swacademy.mapcommunity.domain.entity.User;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User getUser(UUID userId){
        return userRepository.getUser(userId);
    }
}
