package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.vo.LoginInfo;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User login(LoginInfo loginInfo);
    boolean logout (LoginInfo loginInfo);
    User insert(User user);
    User update(User user);
    void delete(User user);
    Optional<User> getUserById(UUID userId);
    Optional<User> getUserByEmail(String email);
}
