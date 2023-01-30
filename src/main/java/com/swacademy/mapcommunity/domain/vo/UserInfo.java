package com.swacademy.mapcommunity.domain.vo;

import java.util.Date;

public record UserInfo(
        String email,
        String name,
        Gender gender,
        Date birth
) {
    public UserInfo {

    }
}
