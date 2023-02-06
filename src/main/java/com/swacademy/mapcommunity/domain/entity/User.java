package com.swacademy.mapcommunity.domain.entity;

import com.swacademy.mapcommunity.domain.vo.Gender;
import com.swacademy.mapcommunity.domain.vo.LoginInfo;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private final UUID userId;
    private final LoginInfo loginInfo;
    private String name;
    private Gender gender;
    private LocalDateTime birth;

    public User(UUID userId, LoginInfo loginInfo, String name, Gender gender, LocalDateTime birth) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.loginInfo = loginInfo;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeGender(Gender gender) {
        this.gender = gender;
    }

    public void changeBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }
}
