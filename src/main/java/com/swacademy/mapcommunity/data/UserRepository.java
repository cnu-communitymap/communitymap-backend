package com.swacademy.mapcommunity.data;

import com.swacademy.mapcommunity.domain.entity.User;
import com.swacademy.mapcommunity.domain.vo.LoginInfo;

import java.util.UUID;

public interface UserRepository {

    User login(LoginInfo loginInfo);
    boolean logout (LoginInfo loginInfo);
    User update(User user);
    User getUser(UUID userId);
    User register(User user);

}
