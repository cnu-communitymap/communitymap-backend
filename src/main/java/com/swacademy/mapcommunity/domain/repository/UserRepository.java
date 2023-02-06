package com.swacademy.mapcommunity.domain.repository;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.vo.LoginInfo;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    // @TODO Auth 관련한 로직을 repository가 알 필요가 있을까? 생각해보기.
    User login(LoginInfo loginInfo);
    boolean logout (LoginInfo loginInfo);
    User insertUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
    Optional<User> getUserById(UUID userId);
    Optional<User> getUserByEmail(String email);
}
