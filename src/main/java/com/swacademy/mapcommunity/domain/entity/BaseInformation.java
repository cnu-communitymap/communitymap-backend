package com.swacademy.mapcommunity.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class BaseInformation {
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public BaseInformation() {}

    public BaseInformation(LocalDateTime createdAt) {
        this(createdAt, null);
    }
}
