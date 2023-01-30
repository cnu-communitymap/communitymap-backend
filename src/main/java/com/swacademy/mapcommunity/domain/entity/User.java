package com.swacademy.mapcommunity.domain.entity;

import com.swacademy.mapcommunity.domain.vo.UserInfo;

import java.util.UUID;

public class User {
    private final UUID userId;
    private final UserInfo userInfo;

    public User(UUID userId, UserInfo user) {
        this.userId = userId;
        this.userInfo = user;
    }

    public UUID getUserId() {
        return userId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
