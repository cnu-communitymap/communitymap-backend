package com.swacademy.mapcommunity.domain.entity;

import com.swacademy.mapcommunity.domain.vo.Gender;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private final UUID userId;
    private final String email;
    private String name;
    private final Gender gender;
    private final LocalDateTime birth;

    public User(UUID userId, String email, String name, Gender gender, LocalDateTime birth) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
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
}
